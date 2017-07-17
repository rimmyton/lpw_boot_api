package com.loupan.boot.config.jdbc;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
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
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactoryCentaec", transactionManagerRef = "transactionManagerCentaec", basePackages = {
		"com.loupan.boot.repository.centaec" }) // 设置Repository所在位置
public class CentaecJPAConfig {

	@Autowired
	@Qualifier("centaecDataSource")
	private DataSource centaecDataSource;

	@Autowired
	private JpaProperties jpaProperties;

	@Primary
	@Bean(name = "entityManagerCentaec")
	public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
		return entityManagerFactoryCentaec(builder).getObject().createEntityManager();
	}

	@Primary
	@Bean(name = "entityManagerFactoryCentaec")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryCentaec(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(centaecDataSource).properties(getVendorProperties(centaecDataSource))
				.packages("com.loupan.boot.domain.centaec") // 设置实体类所在位置
				.persistenceUnit("centaecPersistenceUnit").build();
	}

	private Map<String, String> getVendorProperties(DataSource dataSource) {
		return jpaProperties.getHibernateProperties(dataSource);
	}

	@Primary
	@Bean(name = "transactionManagerCentaec")
	public PlatformTransactionManager transactionManagerCentaec(EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(entityManagerFactoryCentaec(builder).getObject());
	}

}
