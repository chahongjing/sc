package com.zjy.config;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zjy on 2019/4/29.
 */
@RestController
public class TestController {
    @GetMapping("")
    public Object index(String name) {
        return "曾军毅";
    }
}
