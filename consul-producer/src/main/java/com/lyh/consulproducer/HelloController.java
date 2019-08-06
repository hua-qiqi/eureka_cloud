package com.lyh.consulproducer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class HelloController {

    @Value("${server.port}")
    String port;
    @Value("${foo.bar}")
    String foo;

    @GetMapping("/hi")
    public String hi(@RequestParam String name) {
        return "hi "+name+",i am from port:" +foo;
    }
}
