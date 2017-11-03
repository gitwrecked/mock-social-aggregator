package com.gitwrecked.mocksocialaggregator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.gitwrecked" })
public class MockSocialAggregatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MockSocialAggregatorApplication.class, args);
	}
}
