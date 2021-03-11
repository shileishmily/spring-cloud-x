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
 * @author Leo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "zuul.gray")
//@EnableConfigurationProperties(GrayUserConfigProp.class)
public class GrayUserConfigProp {
	
	@Builder.Default
	private List<String> userIdList = Collections.emptyList();
	private String version;
}