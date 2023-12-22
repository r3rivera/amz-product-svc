package com.boogeyman.app.storage;


import jakarta.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Slf4j
@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "AppEntityManagerFactory",
        transactionManagerRef = "AppDBTxnManager",
        basePackages = { "com.boogeyman.app.storage"}
)
@ConditionalOnProperty(name = "app.rdbs.config.enabled")
public class RDBSAppConfiguration {

    @Primary
    @Bean(name="AppDBDataSource")
    @ConfigurationProperties(prefix="app.spring.datasource")
    public DataSource appDataSource(){
        log.info("Creating App Data Source Bean....");
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name="AppEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean appEntityManagerFactoryBean(
            EntityManagerFactoryBuilder builder,
            @Qualifier("AppDBDataSource") DataSource appDatasource
    ){
        log.info("Creating App Entity Manager....");
        return builder
                .dataSource(appDatasource)
                .packages("com.boogeyman.app.storage.entities")
                .persistenceUnit("appDB")
                .build();
    }

    @Primary
    @Bean(name = "AppDBTxnManager")
    public PlatformTransactionManager txnManager(@Qualifier("AppEntityManagerFactory") EntityManagerFactory entityManagerFactory){
        log.info("Creating App Transaction Manager....");
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean
    public JdbcTemplate jdbcTemplate(@Qualifier("AppDBDataSource") DataSource appDatasource){
        return new JdbcTemplate(appDatasource);
    }

}
