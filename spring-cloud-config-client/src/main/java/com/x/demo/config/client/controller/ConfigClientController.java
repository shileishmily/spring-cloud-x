package com.x.demo.config.client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Leo
 */
@RestController
public class ConfigClientController {

    @Value("${pay.url}")
    private String payUrl;

    @Value("${pay.name}")
    private String payName;

    @GetMapping("/getConfig")
    public String getConfig() {
        return "从apollo获取配置：payUrl=" + payUrl + ", payName=" + payName;
    }
}