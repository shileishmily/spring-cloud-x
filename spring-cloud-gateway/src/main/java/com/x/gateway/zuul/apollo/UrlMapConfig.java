package com.x.gateway.zuul.apollo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * 动态路由url配置，从apollo配置中心加载
 * @author Leo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "url")
public class UrlMapConfig {
    private Map<String, String> map;
}