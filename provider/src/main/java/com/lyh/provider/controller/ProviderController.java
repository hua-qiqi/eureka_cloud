package com.lyh.provider.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

@RefreshScope
@RestController
public class ProviderController {

    @Value("${info.profile}")
    private String value;



    @RequestMapping(method = RequestMethod.GET, value = "/queryNameById")
    String queryNameById(@RequestParam(value = "id") Long id){
        //模拟后端数据库查询操作
        return id+value;
    }


}
