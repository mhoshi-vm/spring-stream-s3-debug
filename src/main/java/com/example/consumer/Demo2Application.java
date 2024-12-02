package com.example.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.fn.aggregator.AggregatorFunctionConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.messaging.Message;
import reactor.core.publisher.Flux;

import java.util.function.Consumer;
import java.util.function.Supplier;

@SpringBootApplication
@ComponentScan("org.springframework.cloud.fn.consumer.s3")
@EnableAutoConfiguration(exclude = {AggregatorFunctionConfiguration.class})
public class Demo2Application {

	public static void main(String[] args) {
		SpringApplication.run(Demo2Application.class, args);
	}

	@Autowired
	Consumer<Message<?>> s3Consumer;
}
