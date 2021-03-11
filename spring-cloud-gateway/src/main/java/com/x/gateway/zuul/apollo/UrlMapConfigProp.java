package com.x.gateway.zuul.apollo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.Map;

/**
 * @author Leo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "url")
//@EnableConfigurationProperties(UrlMapConfigProp.class)
public class UrlMapConfigProp {
    private Map<String, String> map;
}