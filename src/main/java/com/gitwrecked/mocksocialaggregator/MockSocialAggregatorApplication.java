package com.gitwrecked.mocksocialaggregator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = { "com.gitwrecked" })
@EnableScheduling
public class MockSocialAggregatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MockSocialAggregatorApplication.class, args);
	}
}
