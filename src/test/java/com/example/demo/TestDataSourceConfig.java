package com.example.demo;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class TestDataSourceConfig {
  private static final String URL = "jdbc:h2:mem:plan;DB_CLOSE_ON_EXIT=false;DATABASE_TO_UPPER=true;MODE=MySQL";
  private static final String USERNAME = "username";
  private static final String PASSWORD = "password";

  @Bean
  public DataSource dataSource() {
    return new DriverManagerDataSource(URL, USERNAME, PASSWORD);
  }
}