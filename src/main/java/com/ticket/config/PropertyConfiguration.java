package com.ticket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource({"classpath:database/sql-query.properties", "classpath:common.properties"})
public class PropertyConfiguration {
}
