package com.wipro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//@EnableSwagger2
@EnableDiscoveryClient
@SpringBootApplication
public class SpingHelloProjectDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpingHelloProjectDemoApplication.class, args);
	}

}
