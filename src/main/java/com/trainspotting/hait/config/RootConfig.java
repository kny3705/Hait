package com.trainspotting.hait.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.trainspotting.hait.jwt.JwtProvider;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@ComponentScan
public class RootConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public JwtProvider jwtProvider() {
		return new JwtProvider("Hate wait service by team Trainspotting.");
	}

	// sql session factory
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(datasource());
		sqlSessionFactory.setTypeAliasesPackage("com.trainspotting.hait");
		return sqlSessionFactory.getObject();
	}

	// hikari cp - datasource
	@Bean
	public DataSource datasource() {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
		hikariConfig.setJdbcUrl("jdbc:mysql://localhost/hatewait?serverTimezone=UTC");
		hikariConfig.setUsername("root");
		hikariConfig.setPassword("root");

		HikariDataSource datasource = new HikariDataSource(hikariConfig);

		return datasource;
	}

	// mail sender
	@Bean
	public JavaMailSender mailsender() {
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		sender.setHost("smtp.gmail.com");
		sender.setPort(587);
		sender.setUsername(null);
		sender.setPassword(null);

		Properties props = new Properties();
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.starttls.enable", "true");

		sender.setJavaMailProperties(props);

		return sender;
	}
}
