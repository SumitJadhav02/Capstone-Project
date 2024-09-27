package com.micro.MicroServiceDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
@EnableEurekaServer
@SpringBootApplication
public class MicroServiceDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServiceDemoApplication.class, args);
	}

}
