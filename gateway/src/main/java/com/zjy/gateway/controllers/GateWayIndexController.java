package com.zjy.gateway.controllers;

import com.zjy.gateway.services.HellowService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;

@RestController
public class GateWayIndexController {

    @Resource
    private HellowService hellowSrv;

    @GetMapping("")
    public Object gateWayIndex(String name) {
        return hellowSrv.hello(Objects.toString(name, "曾军毅"));
    }

    @GetMapping("update")
    public void update() {
        hellowSrv.update();
    }
}
