package com.x.alertmanager.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;

/**
 * @author Leo
 */
@RestController
@RequestMapping("alertMessage")
@Slf4j
public class ReceiveAlertMessageController {

    @PostMapping("receive")
    public String receiveMsg(@RequestBody byte[] data) {
        String msg = new String(data, 0, data.length, Charset.forName("UTF-8"));
        log.info("接收AlertManager预警消息：" + msg);
        return "success";
    }
}
