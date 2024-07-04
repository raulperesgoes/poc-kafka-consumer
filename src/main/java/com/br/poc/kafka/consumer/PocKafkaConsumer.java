package com.br.poc.kafka.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class PocKafkaConsumer {

	public static void main(String[] args) {
		SpringApplication.run(PocKafkaConsumer.class, args);
	}

}
