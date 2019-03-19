package com.cc.framework.springboot.web.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

/**
 *
 * @author sen.hu
 * @date 2019/3/19 11:28
 **/
@Configuration
public class InternationalConfig {

    @Bean
    public LocaleResolver httpHeaderResolver() {
        AcceptHeaderLocaleResolver acceptHeaderLocaleResolver = new AcceptHeaderLocaleResolver();
        return acceptHeaderLocaleResolver;
    }


}
