package com.x.gray.consumer.controller;

import com.google.common.collect.ImmutableMap;
import com.x.gray.consumer.fegin.UserClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping("/test")
public class GrayConsumerController {

    @Autowired
    Environment env;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserClient userClient;

    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public Object me(@RequestParam Long id) {
        log.info("=======>id:{}", id);
        return "me:" + id;
    }

    @RequestMapping(value = "/getId", method = RequestMethod.GET)
    public Object getPath(@RequestHeader(value = "version", required = false) String version, @RequestParam Long id) {
        log.info("=======>id:{},version:{}", id, version);

        Object result = restTemplate.getForObject("http://x-demo-springcloud-gray-provider/user/getId?id=" + id, String.class);
        return ImmutableMap.of("port", env.getProperty("server.port"), "result", result);
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public Object postId(@RequestBody String param) {
        log.info("=======>param:{}", param);

        Object result = restTemplate.postForObject("http://x-demo-springcloud-gray-provider/user/post", param, String.class);
        return ImmutableMap.of("port", env.getProperty("server.port"), "result", result);
    }


    @RequestMapping(value = "/feginGet", method = RequestMethod.GET)
    public Object restTemplatePost(@RequestParam Long id) {
        Object result = userClient.testGet(id);
        return ImmutableMap.of("port", env.getProperty("server.port"), "result", result);
    }

    @RequestMapping(value = "/feginPost", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object restTemplatePost(@RequestBody String body) {
        Object result = userClient.testPost(body);
        return ImmutableMap.of("port", env.getProperty("server.port"), "result", result);
    }

}
