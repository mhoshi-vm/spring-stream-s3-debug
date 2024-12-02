package com.example.supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.fn.aggregator.AggregatorFunctionConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.messaging.Message;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@SpringBootApplication
@ComponentScan("org.springframework.cloud.fn.supplier.s3")
@EnableAutoConfiguration(exclude = {AggregatorFunctionConfiguration.class})
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	Supplier<Flux<Message<?>>> s3Supplier;
}
