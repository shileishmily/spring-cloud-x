package com.x.gray.core;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Leo
 */
@Configuration
@PropertySource(value = "classpath:rule.properties")
@ConfigurationProperties(prefix = "map")
public class RulePropertiesConfig {
	
	private Map<String, String> ribbon = new HashMap<>();

	public Map<String, String> getRibbon() {
		return ribbon;
	}

	public void setRibbon(Map<String, String> ribbon) {
		this.ribbon = ribbon;
	}
}
