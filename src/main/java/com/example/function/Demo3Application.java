package com.example.function;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.function.Function;


@SpringBootApplication
//@ComponentScan("org.springframework.cloud.fn.supplier.s3")
//@Import(org.springframework.cloud.fn.supplier.s3.AwsS3SupplierConfiguration.class)
public class Demo3Application {

	public static void main(String[] args) {
		SpringApplication.run(Demo3Application.class, args);
	}

	@Bean
	Function<Message<?>, Message<?>> hoge() {
		return (min) -> {

			DateFormat coverter = new SimpleDateFormat("yyyyMMddHHmmss");

			String reportName;
			reportName = coverter.format(
					new Date(Long.parseLong(Objects.requireNonNull(min.getHeaders().get("timestamp")).toString()))
			) + "_report.txt";

			//System.out.println("Original: " + min);
			return MessageBuilder
					.withPayload(min.getHeaders().get("file_relativePath"))
					.setHeader("report_name", reportName)
					.build();
		};
	}


	/*
	@Bean
	Supplier<Flux<Long>> hoge(){
		return () -> Flux.interval(Duration.ofSeconds(3)).doOnNext(System.out::println);
	}*/
}
