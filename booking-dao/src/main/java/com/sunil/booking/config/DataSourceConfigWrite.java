package com.sunil.booking.config;

import com.sunil.booking.annotation.ReadOnlyRepository;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import java.util.Properties;

@Configuration
@ConfigurationProperties(prefix = "spring.write.datasource")
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactoryWrite", 
	transactionManagerRef = "transactionManager", 
	excludeFilters = @ComponentScan.Filter(ReadOnlyRepository.class),
	basePackages = {"com.sunil.booking.repository.write" })
public class DataSourceConfigWrite extends HikariConfig {

	private String persistenceUnitName = "write";
	private String modelPackage = "com.sunil.booking.model";
	
   public DataSourceConfigWrite() {
       setMinimumIdle(5);
       setMaximumPoolSize(10);
       setIdleTimeout(25000);
       setMaxLifetime(250000);
       setConnectionTimeout(30000);
       setDriverClassName("org.mariadb.jdbc.Driver");
	   setJdbcUrl("jdbc:mariadb://localhost:3306/booking");
	   setUsername("root");
	   setPassword("root");
   }
		    
	@Bean
	@Primary
	public HikariDataSource dataSourceWrite() {
		return new HikariDataSource(this);
	}

	@Bean
	@Primary
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryWrite(final HikariDataSource dataSourceWrite) {
		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		localContainerEntityManagerFactoryBean.setDataSource(dataSourceWrite);
		localContainerEntityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		localContainerEntityManagerFactoryBean.setPersistenceUnitName(persistenceUnitName);
		localContainerEntityManagerFactoryBean.setPackagesToScan(modelPackage);
		localContainerEntityManagerFactoryBean.setJpaProperties(getJpaProperties());
		return localContainerEntityManagerFactoryBean;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactoryWrite) {
		return new JpaTransactionManager(entityManagerFactoryWrite);
	}
	
	private Properties getJpaProperties() {
		Properties jpaProperties = new Properties();
		jpaProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
		jpaProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		jpaProperties.setProperty("hibernate.ddl-auto", "update");
		jpaProperties.setProperty("show-sql", "false");
		jpaProperties.setProperty("show-sql", "false");
		jpaProperties.setProperty("org.hibernate.envers.audit_table_suffix", "_aud");
		return jpaProperties;
	}
}