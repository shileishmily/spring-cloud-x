package com.x.demo.feign.controller;

import com.x.demo.feign.service.FeignClientDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("feign")
public class FeignClientDemoController {

    @Autowired
    FeignClientDemoService feignClientDemoService;

    @RequestMapping(value = "service", method = RequestMethod.GET)
    public String service() {
        return feignClientDemoService.service();
    }
}
