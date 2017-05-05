package com.sjd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class Kubernetes1Application {

	public static void main(String[] args) {
		SpringApplication.run(Kubernetes1Application.class, args);
	}
}
