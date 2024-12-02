package com.example.function;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.fn.aggregator.AggregatorFunctionConfiguration;
import org.springframework.cloud.fn.aggregator.ExcludeStoresAutoConfigurationEnvironmentPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.integration.IntegrationMessageHeaderAccessor;
import org.springframework.integration.config.AggregatorFactoryBean;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.function.Function;


@SpringBootApplication
@EnableAutoConfiguration(exclude = {AggregatorFunctionConfiguration.class})
public class Demo3Application {

	public static void main(String[] args) {
		SpringApplication.run(Demo3Application.class, args);
	}

	@Bean
	Function<Message<?>, Message<?>> hoge() {
		return (min) -> {

			DateFormat converter = new SimpleDateFormat("yyyyMMddHHmmss");
			Date timestamp = new Date(Long.parseLong(Objects.requireNonNull(min.getHeaders().get("timestamp")).toString()));

			String reportName;
			reportName = converter.format(timestamp) + "_report.txt";

			System.out.println("Original: " + min);
			return MessageBuilder
					.withPayload(min.getHeaders().get("file_relativePath"))
					.setHeader("report_name", reportName)
					.setHeader(IntegrationMessageHeaderAccessor.CORRELATION_ID, converter.format(timestamp))
					.build();
		};
	}
}
