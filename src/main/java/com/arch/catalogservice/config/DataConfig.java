package com.arch.catalogservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;

//for jpa we can use @EnableJpaAuditing adn at entity class use @EntityListeners(AuditingEntityListener.class)
@Configuration
@EnableJdbcAuditing
public class DataConfig {}
