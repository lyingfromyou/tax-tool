package com.example.taxtool.config;


import com.example.taxtool.service.MinioTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * minio 自动配置类
 */
//@AllArgsConstructor
//@EnableConfigurationProperties({MinioProperties.class})
@Configuration
public class MinioAutoConfiguration {
    @Autowired
    private MinioProperties properties;

    @Bean
    @ConditionalOnMissingBean(MinioTemplate.class)
    @ConditionalOnProperty(name = "minio.url")
    public MinioTemplate template() {
        return new MinioTemplate(
                properties.getUrl(),
                properties.getAccessKey(),
                properties.getSecretKey()
        );
    }

}
