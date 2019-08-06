package com.lyh.consumer.service;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class ComputeClientHystrix implements  ComputeClient{


    @Override
    public String queryNameById(Long id) {
        return "服务降级";
    }
}
