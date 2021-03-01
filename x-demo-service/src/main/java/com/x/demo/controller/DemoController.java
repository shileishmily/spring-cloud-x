package com.x.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "第一个测试Controller")
@RestController
@RequestMapping("demo")
@Slf4j
public class DemoController {

    @Value("${server.port}")
    String port;

    @ApiOperation(value = "撩我接口")
    @GetMapping("service")
    public String service() {
        log.info("谁在撩我?");
        return "x-demo-service hello." + port;
    }
}
