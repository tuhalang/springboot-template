package com.example.base.api.config.datasource;

import com.example.base.common.domain.dba.DomainBase;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@EnableJpaAuditing
@EntityScan(basePackageClasses = DomainBase.class)
@EnableJpaRepositories(basePackageClasses = DomainBase.class)
@Configuration
public class DataSourceAConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "app.datasource.a.master")
    public HikariConfig masterDataSourceConfig() {
        return new HikariConfig();
    }

    @Bean
    @ConfigurationProperties(prefix = "app.datasource.a.slave")
    public HikariConfig slaveDataSourceConfig() {
        return new HikariConfig();
    }

    @Bean(name = "myDatasourceA")
    public DataSource myDatasource(HikariConfig masterDataSourceConfig, HikariConfig slaveDataSourceConfig) {
        RoutingDataSource routingDataSource = new RoutingDataSource();
        DataSource masterDataSource = new HikariDataSource(masterDataSourceConfig);
        DataSource slaveDataSource = new HikariDataSource(slaveDataSourceConfig);

        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(RouterDbType.MASTER, masterDataSource);
        dataSourceMap.put(RouterDbType.SLAVE, slaveDataSource);

        routingDataSource.setTargetDataSources(dataSourceMap);
        routingDataSource.setDefaultTargetDataSource(masterDataSource);
        routingDataSource.afterPropertiesSet();

        return new LazyConnectionDataSourceProxy(routingDataSource);
    }
}
