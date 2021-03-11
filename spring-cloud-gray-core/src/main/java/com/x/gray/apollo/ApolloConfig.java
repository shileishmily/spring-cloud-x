package com.x.gray.apollo;

import org.springframework.context.annotation.Bean;


/**
 * @author Leo
 */
public class ApolloConfig {
	
	@Bean
    public ConfigChangeListen javaConfigSample(){
        return new ConfigChangeListen();
    }
}
