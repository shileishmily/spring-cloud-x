package com.x.gray.consumer.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Leo
 */
@FeignClient(name = "x-demo-springcloud-gray-provider")
public interface UserClient {

    @RequestMapping(value = "/user/getId", method = RequestMethod.GET)
    Object testGet(@RequestParam("id") Long id);

    @RequestMapping(value = "/user/post", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Object testPost(@RequestBody String body);

}
