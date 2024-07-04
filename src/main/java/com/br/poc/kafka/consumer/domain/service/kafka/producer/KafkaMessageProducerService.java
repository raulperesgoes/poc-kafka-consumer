package com.br.poc.kafka.consumer.domain.service.kafka.producer;

import com.br.poc.kafka.consumer.dto.MessageDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class KafkaMessageProducerService {

    private final KafkaTemplate<String, MessageDto> kafkaTemplate;

    public KafkaMessageProducerService(KafkaTemplate<String, MessageDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void producer(MessageDto messageDto) {
        this.kafkaTemplate.send("poc-kafka-consumer", messageDto);
    }
}