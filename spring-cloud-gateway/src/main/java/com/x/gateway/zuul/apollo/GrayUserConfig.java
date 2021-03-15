package com.x.gateway.zuul.apollo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.Collections;
import java.util.List;

/**
 * 灰度用户列表，版本配置，从apollo配置中心加载
 * @author Leo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "zuul.gray")
public class GrayUserConfig {
	
	@Builder.Default
	private List<String> userIdList = Collections.emptyList();
	private String version;
}