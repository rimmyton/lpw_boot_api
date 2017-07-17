package com.loupan.boot.config.jdbc;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataSourceConfig {

	@Bean(name = "centaecDataSource")
	@Qualifier("centaecDataSource")
	@Primary
	@ConfigurationProperties(prefix = "centaec.jdbc")
	public DataSource centaecDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "unionDataSource")
	@Qualifier("unionDataSource")
	@ConfigurationProperties(prefix = "union.jdbc")
	public DataSource unionDataSource() {
		return DataSourceBuilder.create().build();
	}

}
