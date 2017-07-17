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
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactoryUnion", transactionManagerRef = "transactionManagerUnion", basePackages = {
		"com.loupan.boot.repository.union" }) // 设置Repository所在位置
public class UnionJPAConfig {

	@Autowired
	@Qualifier("unionDataSource")
	private DataSource unionDataSource;

	@Autowired
	private JpaProperties jpaProperties;

	@Bean(name = "entityManagerUnion")
	public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
		return entityManagerFactoryUnion(builder).getObject().createEntityManager();
	}

	@Bean(name = "entityManagerFactoryUnion")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryUnion(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(unionDataSource).properties(getVendorProperties(unionDataSource))
				.packages("com.loupan.boot.domain.union") // 设置实体类所在位置
				.persistenceUnit("unionPersistenceUnit").build();
	}

	private Map<String, String> getVendorProperties(DataSource dataSource) {
		return jpaProperties.getHibernateProperties(dataSource);
	}

	@Bean(name = "transactionManagerUnion")
	public PlatformTransactionManager transactionManagerUnion(EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(entityManagerFactoryUnion(builder).getObject());
	}

}
