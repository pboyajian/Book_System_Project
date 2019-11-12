package com.trilogyed.bookserviceeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class BookServiceEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookServiceEurekaApplication.class, args);
	}

}
