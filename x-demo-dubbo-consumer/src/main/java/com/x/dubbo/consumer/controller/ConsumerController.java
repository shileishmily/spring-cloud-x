package com.x.dubbo.consumer.controller;

import com.x.dubbo.consumer.protocal.CallRequest;
import com.x.dubbo.consumer.util.DubboCallbackUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Leo
 */
@RestController
@RequestMapping(value = "/consumer")
public class ConsumerController {

    @Value("${dubbo.registry.address}")
    private String address;

    @RequestMapping(value = "/callJson")
    @ResponseBody
    public Object call(@RequestBody CallRequest request) {
        return DubboCallbackUtil.invoke(request.getInterfaceName(), request.getMethod(), request.getParam(), address, request.getVersion());
    }
}




