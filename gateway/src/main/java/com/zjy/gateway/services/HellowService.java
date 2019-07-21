package com.zjy.gateway.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "spring-cloud-producer123", fallback = HellowServiceFailure.class)
public interface HellowService {
    @RequestMapping(value = "/hello")
    @HystrixCommand
    String hello(@RequestParam(value = "name") String name);

    @RequestMapping(value = "/update")
    void update();
}