package com.lyh.consulconsumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "consul-provider")
@Service
public interface HiService {


    @GetMapping(value = "/hi")
    String hi(@RequestParam(value = "name") String name);
}
