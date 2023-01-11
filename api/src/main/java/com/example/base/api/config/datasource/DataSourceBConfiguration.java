package com.example.base.api.config.datasource;

import com.example.base.common.domain.dbb.DomainBase;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;

@EnableJpaAuditing
@EntityScan(basePackageClasses = DomainBase.class)
@EnableJpaRepositories(basePackageClasses = DomainBase.class)
@Configuration
public class DataSourceBConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "app.datasource.b")
    public HikariConfig dataSourceBConfig() {
        return new HikariConfig();
    }


    @Bean(name = "myDatasourceB")
    public DataSource myDatasource(HikariConfig dataSourceBConfig) {
        return new HikariDataSource(dataSourceBConfig);
    }
}
