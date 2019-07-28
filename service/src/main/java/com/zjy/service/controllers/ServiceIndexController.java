package com.zjy.service.controllers;

import brave.Tracer;
import com.zjy.service.po.User;
import com.zjy.service.services.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
// sc config配置刷新配置方式
@RefreshScope
public class ServiceIndexController {

    @Autowired
    private UserService userSrv;

    @Autowired
    private Tracer tracer;

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${server.port}")
    private String port;

//    @Value("${configname}")
    private String configname;

    @GetMapping("/hello")
    @HystrixCommand(fallbackMethod = "getHostMessageFallback")
    public Map serviceIndex(@RequestParam String name) {
        Map<String, String> map = new HashMap<>();
        map.put("configName", "conf");
//        map.put("configName", configname);
        map.put("applicationName", applicationName);
        map.put("port", port);
        String spanId = tracer.currentSpan().context().spanIdString();
        String traceId = tracer.currentSpan().context().traceIdString();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        map.put("datetime", dateTimeFormatter.format(LocalDateTime.now()));
//        User user = userSrv.get(1L);
        User user = null;
        List<String> names = new ArrayList<>();
        if (StringUtils.isNotBlank(name)) {
            map.put("paramName", name);
        }
        if (user != null && StringUtils.isNotBlank(user.getName())) {
            map.put("dbName", user.getName());
        }
        System.out.println("数据来源：" + port);
        return map;
    }

    public Map getHostMessageFallback(@RequestParam String name) {
        Map<String, String> map = new HashMap<>();
        map.put("fail", "true");
        return map;
    }

    @GetMapping("/update")
    public void update() {
        try {
            userSrv.update();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @PostMapping("/post")
    public String postTest(@RequestParam String name) {
        return name;
    }
}
