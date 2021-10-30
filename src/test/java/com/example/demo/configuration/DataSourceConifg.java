package com.example.demo.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.springtestdbunit.bean.DatabaseConfigBean;
import com.github.springtestdbunit.bean.DatabaseDataSourceConnectionFactoryBean;

@Configuration
public class DataSourceConifg {

	@Bean
	public DatabaseConfigBean dbUnitDatabaseConfig() {
		DatabaseConfigBean bean = new DatabaseConfigBean();

		bean.setAllowEmptyFields(true);

		return bean;
	}

	@Bean
	public DatabaseDataSourceConnectionFactoryBean dbUnitDatabaseConnection(DatabaseConfigBean dbUnitDatabaseConfig,
			DataSource dataSource) {
		DatabaseDataSourceConnectionFactoryBean bean = new DatabaseDataSourceConnectionFactoryBean(dataSource);
		bean.setDatabaseConfig(dbUnitDatabaseConfig);
		return bean;
	}
}