package com.x.demo.feign.controller;

import com.x.demo.feign.client.DemoApiClient;
import com.x.demo.protocol.BaseRequest;
import com.x.demo.protocol.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("feign/api")
public class DemoApiConsumerController {
    @Autowired
    private DemoApiClient demoApiClient;

    @RequestMapping(value = "service", method = RequestMethod.GET)
    public BaseResponse service() {
        BaseRequest request = new BaseRequest();
        request.setUuid("123");
        return demoApiClient.sayHello(request);
    }
}
