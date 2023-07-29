package ru.test.webzaim.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
/**
 * Конфигурация SpringLiquibase
 *
 * @author Ilya Kaltygin
 */
@Configuration
public class LiquibaseConfig {


    @Bean
    public SpringLiquibase liquibase(DataSource ds) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(ds);
        liquibase.setChangeLog("classpath:db/liquibase-changelog.xml");
        return liquibase;
    }
}
