package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.Message;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.function.Supplier;

@SpringBootApplication
//@ComponentScan("org.springframework.cloud.fn.supplier.s3")
//@Import(org.springframework.cloud.fn.supplier.s3.AwsS3SupplierConfiguration.class)
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}



	/*

	@Bean
	Supplier<Flux<Long>> hoge(){
		return () -> Flux.interval(Duration.ofSeconds(3)).doOnNext(System.out::println);
	}*/
}
