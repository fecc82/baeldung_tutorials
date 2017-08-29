package com.psunix.tutorial.baeldung.springcloud.api_gateway.Filters.configurations;

import com.psunix.tutorial.baeldung.springcloud.api_gateway.Filters.AddRequestHeaderFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZuulConfig {
    @Bean
    public AddRequestHeaderFilter getRequestHeaderFilter(){
        return new AddRequestHeaderFilter();
    }
}
