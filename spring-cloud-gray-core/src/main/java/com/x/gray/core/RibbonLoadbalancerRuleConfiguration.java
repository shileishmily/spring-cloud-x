package com.x.gray.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class RibbonLoadbalancerRuleConfiguration implements InitializingBean {
	
	@Autowired
	RulePropertiesConfig rulePropertiesConfig;
	
	
	private static final String RIBBON = "ribbon.";
	private static final Logger logger = LoggerFactory.getLogger(RibbonLoadbalancerRuleConfiguration.class);
	
	@Override
	public void afterPropertiesSet() throws Exception {
		Map<String, String> ribbonMap = rulePropertiesConfig.getRibbon();
		logger.info("init ribbonMap finished");
		
		if(ribbonMap.isEmpty()) {
			throw new RuntimeException("ribbonMap must be not empty");
		}
		ribbonMap.forEach((k,v)->{
			System.setProperty(RIBBON+k,v);
		});
	}
}