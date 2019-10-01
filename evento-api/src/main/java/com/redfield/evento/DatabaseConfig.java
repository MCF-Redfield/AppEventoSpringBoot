package com.redfield.evento;

import javax.sql.DataSource;

import org.hibernate.dialect.Database;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabaseConfig {

	@Value("${spring.datasource.url}")
	  private String dbUrl;

	  @Bean
	  public DataSource dataSource() {
	      HikariConfig config = new HikariConfig();
	      config.setJdbcUrl(dbUrl);
	      return new HikariDataSource(config);
	  }
	  
	  public JpaVendorAdapter jpaVendorAdapter() {
		   HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		   adapter.setDatabase(org.springframework.orm.jpa.vendor.Database.POSTGRESQL);
		   adapter.setShowSql(true);
		   adapter.setGenerateDdl(true);
		   adapter.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect");
		   adapter.setPrepareConnection(true);
		   return adapter;
		   }
}
