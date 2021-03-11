package com.x.gray.annotation;

import com.x.gray.apollo.ApolloConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(ApolloConfig.class)
@Documented
@Inherited
public @interface EnableGrayConfig {

}