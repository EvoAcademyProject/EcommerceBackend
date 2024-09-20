package com.backend.ecommercebackend.config;


import javax.sql.DataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.backend.ecommercebackend.repository.user",
        entityManagerFactoryRef = "firstEntityManagerFactory",
        transactionManagerRef = "firstTransactionManager"
)
public class UserDbConfig {

  @Primary
  @Bean(name = "firstDataSource")
  public DataSource firstDataSource() {
    DataSourceBuilder<?> builder = DataSourceBuilder.create();
    builder.url("jdbc:postgresql://localhost:5437/User");
    builder.username("SECURITY");
    builder.password("SECURITY");
    builder.driverClassName("org.postgresql.Driver");
    return builder.build();
  }


  @Primary
  @Bean(name = "firstEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean firstEntityManagerFactory(
          EntityManagerFactoryBuilder builder) {
    return builder
            .dataSource(firstDataSource())
            .packages("com.backend.ecommercebackend.model")
            .persistenceUnit("first")
            .build();
  }

  @Primary
  @Bean(name = "firstTransactionManager")
  public PlatformTransactionManager firstTransactionManager(
          @Qualifier("firstEntityManagerFactory") EntityManagerFactory firstEntityManagerFactory) {
    return new JpaTransactionManager(firstEntityManagerFactory);
  }
}
