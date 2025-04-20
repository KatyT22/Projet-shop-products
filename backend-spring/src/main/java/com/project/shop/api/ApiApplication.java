package com.project.shop.api;

import com.project.shop.api.repository.ProductRepository;
import com.project.shop.api.service.ProductService;
import com.project.shop.api.service.ProductServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}
