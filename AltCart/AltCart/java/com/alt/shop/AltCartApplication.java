package com.alt.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author SYEDYASIRASWATH
 *
 */
@SpringBootApplication(scanBasePackages="com.alt.shop")
@EnableJpaRepositories
@EntityScan(basePackages="com.alt.shop.entity")
@EnableConfigurationProperties
@PropertySource(value = {"classpath:configuration.properties"})
public class AltCartApplication {

	public static void main(String[] args) {
		SpringApplication.run(AltCartApplication.class, args);
	}
}
