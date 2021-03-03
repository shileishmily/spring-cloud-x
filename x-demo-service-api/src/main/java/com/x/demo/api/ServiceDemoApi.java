package com.x.demo.api;

import com.x.demo.protocol.BaseRequest;
import com.x.demo.protocol.BaseResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Leo
 */
@RequestMapping("demo-api")
public interface ServiceDemoApi {

    @PostMapping("sayHello")
    BaseResponse sayHello(@RequestBody BaseRequest request);
}
