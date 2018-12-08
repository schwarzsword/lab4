package config.database;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource("classpath:database.properties")
@ComponentScan("config.database.persistence")
public class DatabaseConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer configurer() {

        return new PropertySourcesPlaceholderConfigurer();
    }
}