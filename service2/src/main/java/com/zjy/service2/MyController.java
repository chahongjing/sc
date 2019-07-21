package com.zjy.service2;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MyController {

    @GetMapping("/getHostMessage/{id}")
    @HystrixCommand(fallbackMethod = "getHostMessageFallback")
    public Map<String, Object> getHostMessage(@PathVariable String id) {

        if ("error".equals(id)) {
            throw new RuntimeException("测试异常演习！");
        }

        Map<String, Object> map = new HashMap<>();
        try {
            InetAddress serverHost = InetAddress.getLocalHost();
            map.put("hostname", serverHost.getHostName());
            map.put("hostAddress", serverHost.getHostAddress());
            map.put("id", id);

            return map;
        } catch (UnknownHostException e) {
            e.printStackTrace();
            map.put("msg", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

    }

    public Map<String, Object> getHostMessageFallback(@PathVariable String id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("description", "异常演习Fallback！");
        return map;

    }
}