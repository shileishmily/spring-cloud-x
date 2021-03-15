package com.x.gray.apollo;

import org.springframework.context.annotation.Bean;


/**
 * @author Leo
 */
public class ApolloConfig {
	
	@Bean
    public ConfigChangeListener configChangeListener(){
        return new ConfigChangeListener();
    }
}
