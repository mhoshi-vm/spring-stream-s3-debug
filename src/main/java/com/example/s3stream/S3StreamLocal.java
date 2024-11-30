package com.example.s3stream;

import org.springframework.context.annotation.Bean;
import org.springframework.integration.IntegrationMessageHeaderAccessor;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Component
class S3StreamLocal {



    Supplier<Flux<Message<?>>> s3Supplier;

    Consumer<Message<?>> s3Consumer;

    public S3StreamLocal(Supplier<Flux<Message<?>>> s3Supplier, Consumer<Message<?>> s3Consumer) {
        this.s3Supplier = s3Supplier;
        this.s3Consumer = s3Consumer;
    }

    @Bean
    Function<Message<?>, Message<?>> hoge() {
        return (min) -> {

            DateFormat converter = new SimpleDateFormat("yyyyMMddHHmm");
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

    @Bean
    public Function<String, String> uppercase() {
        return value -> value.toUpperCase();
    }

    @Bean
    Function<Message<?>, Message<?>> foo() {
        return (min) -> {
            System.out.println("AAAAAAAAAAAAAA "+min);
            return null;
        };
    }
}

