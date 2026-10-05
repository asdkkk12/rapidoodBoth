package com.rm2pt.rapidood.api.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rm2pt.rapidood.transform.AtlTransformService;
import com.rm2pt.rapidood.transform.service.DesignGenerateService;

/**
 * Spring bean definitions for the service layer.
 */
@Configuration
@EnableConfigurationProperties(RapidoodPathProperties.class)
public class AppConfig {

    @Bean
    public AtlTransformService atlTransformService() {
        return new AtlTransformService();
    }

    @Bean
    public DesignGenerateService designGenerateService(AtlTransformService atlTransformService) {
        return new DesignGenerateService(atlTransformService);
    }
}
