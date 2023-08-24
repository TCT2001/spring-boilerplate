package com.tct.springboot.configuration.persistance;

import com.tct.springboot.utils.ProjectConstants;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource({"classpath:persistence.properties"})
@EnableJpaRepositories(
        // basePackages = "com.example.webjpa.repositories.db1",
        entityManagerFactoryRef = "userLocalEntityManagerFactory",
        transactionManagerRef = "userTransactionManager"
)
@RequiredArgsConstructor
public class PersistenceUserAutoConfig {
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource userDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean userLocalEntityManagerFactory(
            @Qualifier(value = "hibernateProperties") Properties properties,
            @Qualifier(value = "userDataSource") DataSource dataSource
    ) {
        final var em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.tct.springboot.model.users");
        em.setPersistenceUnitName(ProjectConstants.USER_DB_UNIT_NAME);

        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaProperties(properties);

        return em;
    }

    @Primary
    @Bean
    @DependsOn("userLocalEntityManagerFactory")
    public PlatformTransactionManager userTransactionManager(@Qualifier("userLocalEntityManagerFactory") LocalContainerEntityManagerFactoryBean emf) {
        final var transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf.getObject());
        return transactionManager;
    }

}