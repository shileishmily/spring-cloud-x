package com.x.gray.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author Leo
 */
@Slf4j
public class RibbonLoadbalancerRuleConfiguration implements InitializingBean {

    @Autowired
    RulePropertiesConfig rulePropertiesConfig;


    private static final String RIBBON = "ribbon.";

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, String> ribbonMap = rulePropertiesConfig.getRibbon();
        log.info("init ribbonMap finished");

        if (ribbonMap.isEmpty()) {
            throw new RuntimeException("ribbonMap must be not empty");
        }
        ribbonMap.forEach((k, v) -> {
            System.setProperty(RIBBON + k, v);
        });
    }
}