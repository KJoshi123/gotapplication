package com.got;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author Hi kaustubh ! !
 *
 */
@SpringBootApplication
public class GotApplication extends SpringBootServletInitializer{
	
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(GotApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(GotApplication.class, args);
	}

}
