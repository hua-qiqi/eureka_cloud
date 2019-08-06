package com.lyh.consumer.controller;


import com.lyh.consumer.service.ComputeClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ComputeController {

    private final Logger logger = LoggerFactory.getLogger(ComputeController.class);

    @Autowired
    ComputeClient computeClient;

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    RestTemplate restTemplate;


    /**
     * loadBalancerClient方式负载均衡访问集群接口
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/queryNameById")
    String queryNameById(@RequestParam(value = "id") Long id){
        ServiceInstance serviceInstance = loadBalancerClient.choose("feign-provider");
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/queryNameById?id="+id;
        System.out.println(url);
        return restTemplate.getForObject(url, String.class);
    }

    /**
     * ribbion方式负载均衡访问集群接口
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/queryNameById1")
    String queryNameById1(@RequestParam(value = "id") Long id){
        String url = "http://feign-provider/queryNameById?id="+id;
        return restTemplate.getForObject(url, String.class);
    }

    /**
     * 通过定义的feign客户端来调用服务提供方的接口
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/queryNameById2")
    String queryNameById2(@RequestParam(value = "id") Long id){

        return computeClient.queryNameById(id);
    }

}
