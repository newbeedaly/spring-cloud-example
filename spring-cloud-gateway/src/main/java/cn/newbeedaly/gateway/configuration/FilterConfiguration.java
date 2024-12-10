package cn.newbeedaly.gateway.configuration;

import cn.newbeedaly.gateway.filter.CustomGlobalFilter;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {

    @Bean
    public GlobalFilter customFilter() {
        return new CustomGlobalFilter();
    }
}
