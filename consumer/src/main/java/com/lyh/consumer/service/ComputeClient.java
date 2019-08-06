package com.lyh.consumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="feign-provider",fallback = ComputeClientHystrix.class)
@Service
public interface ComputeClient {
    @RequestMapping(method = RequestMethod.GET, value = "/queryNameById")
    String queryNameById(@RequestParam(value = "id") Long id);
}
