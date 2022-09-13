package com.devsuperior.dsmeta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.devsuperior.dsmeta"})
public class DsmetaApplication{

	public static void main(String[] args) {
		SpringApplication.run(DsmetaApplication.class, args);
	}
}