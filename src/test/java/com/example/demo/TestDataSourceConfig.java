package com.example.demo;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class TestDataSourceConfig {
  private static final String URL = "jdbc:postgresql://localhost:5432/test";
  private static final String USERNAME = "postgres";
  private static final String PASSWORD = "postgres";

  @Bean
  public DataSource dataSource() {
    return new DriverManagerDataSource(URL, USERNAME, PASSWORD);
  }
}