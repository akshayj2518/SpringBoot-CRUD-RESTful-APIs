package com.homelane.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.homelane.demo.service.HomeService;

@SpringBootApplication
public class HomePricePredicationApplication {

	
	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(HomePricePredicationApplication.class, args);
        HomeService service = applicationContext.getBean(HomeService.class);
        service.importData();
	}
	
}
