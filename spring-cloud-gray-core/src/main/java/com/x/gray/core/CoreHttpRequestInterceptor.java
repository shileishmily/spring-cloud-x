package com.x.gray.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;

import java.io.IOException;

/**
 * @author Leo
 */
@Slf4j
public class CoreHttpRequestInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        HttpRequestWrapper requestWrapper = new HttpRequestWrapper(request);
        String hystrixVer = CoreHeaderInterceptor.version.get();
        log.info("set header version={} in CoreHttpRequestInterceptor", hystrixVer);

        requestWrapper.getHeaders().add(CoreHeaderInterceptor.HEADER_VERSION, hystrixVer);
        return execution.execute(requestWrapper, body);
    }
}