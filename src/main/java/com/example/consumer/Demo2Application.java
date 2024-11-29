package com.example.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan("org.springframework.cloud.fn.supplier.s3")
//@Import(org.springframework.cloud.fn.supplier.s3.AwsS3SupplierConfiguration.class)
public class Demo2Application {

	public static void main(String[] args) {
		SpringApplication.run(Demo2Application.class, args);
	}



	/*
	@Bean
	Supplier<Flux<Long>> hoge(){
		return () -> Flux.interval(Duration.ofSeconds(3)).doOnNext(System.out::println);
	}*/
}
