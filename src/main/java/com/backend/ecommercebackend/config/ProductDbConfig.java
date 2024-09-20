package com.backend.ecommercebackend.config;


import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.backend.ecommercebackend.repository.product",
        entityManagerFactoryRef = "secondEntityManagerFactory",
        transactionManagerRef = "secondTransactionManager"
)

public class ProductDbConfig {

    @Value("${spring.datasource.second.url}")
    private String secondDbUrl;

    @Value("${spring.datasource.second.username}")
    private String secondDbUsername;

    @Value("${spring.datasource.second.password}")
    private String secondDbPassword;


    @Bean(name = "secondDataSource")
    public DataSource secondDataSource() {
        DataSourceBuilder<?> builder = DataSourceBuilder.create();
        builder.url(secondDbUrl);
        builder.username(secondDbUsername);
        builder.password(secondDbPassword);
        return builder.build();
    }


    @Bean(name = "secondEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean secondEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(secondDataSource())
                .packages("com.backend.ecommercebackend.model")
                .persistenceUnit("second")
                .build();
    }

    @Bean(name = "secondTransactionManager")
    public PlatformTransactionManager secondTransactionManager(
            @Qualifier("secondEntityManagerFactory") EntityManagerFactory secondEntityManagerFactory) {
        return new JpaTransactionManager(secondEntityManagerFactory);
    }
}