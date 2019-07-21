package com.zjy.gateway.services;

import org.springframework.stereotype.Component;

/**
 * Created by zjy on 2019/4/16.
 */
@Component
public class HellowServiceFailure implements HellowService {
    @Override
    public String hello(String name) {
        System.out.println("hello world service is not available !");
        return "hello world service is not available !";
    }

    @Override
    public void update() {
        System.out.println("update service is not available !");
    }
}
