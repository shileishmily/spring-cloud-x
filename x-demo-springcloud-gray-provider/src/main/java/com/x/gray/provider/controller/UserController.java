package com.x.gray.provider.controller;

import com.netflix.appinfo.EurekaInstanceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Leo
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    Environment env;

    @Autowired
    EurekaInstanceConfig config;

    public static String getIp() {
        String ip = null;
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return ip;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private Map<Long, User> users = new HashMap() {
        private static final long serialVersionUID = 1L;

        {
            put(111L, new User(111L, "aaa", getIp()));
            put(666L, new User(666L, "bbb", getIp()));
            put(888L, new User(888L, "ccc", getIp()));
        }
    };


    @RequestMapping(value = "/getId", method = RequestMethod.GET)
    public User getId(@RequestParam Long id) {
        User user = users.get(id);
        user.setPort(env.getProperty("server.port"));
        return user;

    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public User insert(@RequestBody User user) {
        user.setPort(env.getProperty("server.port"));
        return user;
    }

}