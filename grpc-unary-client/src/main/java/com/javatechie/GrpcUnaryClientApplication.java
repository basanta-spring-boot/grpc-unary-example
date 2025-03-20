package com.javatechie;

import com.javatechie.service.StockClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GrpcUnaryClientApplication implements CommandLineRunner {

	@Autowired
	private StockClientService stockClientService;

	public static void main(String[] args) {
		SpringApplication.run(GrpcUnaryClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Found stock : "+ stockClientService.getStockPrice("AMZN"));
	}
}
