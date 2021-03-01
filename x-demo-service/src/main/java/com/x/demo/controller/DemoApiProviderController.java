package com.x.demo.controller;

import com.x.demo.api.ServiceDemoApi;
import com.x.demo.protocol.BaseRequest;
import com.x.demo.protocol.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Leo
 */
@Api(tags = "DemoApiProviderController")
@RestController
@Slf4j
public class DemoApiProviderController implements ServiceDemoApi {

    @Value("${server.port}")
    String port;

    @ApiOperation(value = "sayHello接口")
    @Override
    public BaseResponse sayHello(BaseRequest request) {
        BaseResponse response = new BaseResponse();
        response.setCode("0000");
        response.setMessage("success");
        response.setData("ServiceDemoApi: " + port);
        return response;
    }
}
