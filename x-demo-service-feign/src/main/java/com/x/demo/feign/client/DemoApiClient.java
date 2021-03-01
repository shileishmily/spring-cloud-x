package com.x.demo.feign.client;

import com.x.demo.api.ServiceDemoApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "x-demo-service")
public interface DemoApiClient extends ServiceDemoApi {

}
