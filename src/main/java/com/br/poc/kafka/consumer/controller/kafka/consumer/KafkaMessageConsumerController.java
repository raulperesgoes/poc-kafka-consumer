package com.br.poc.kafka.consumer.controller.kafka.consumer;

import com.br.poc.kafka.consumer.domain.model.entity.MessagesEntity;
import com.br.poc.kafka.consumer.domain.service.aws.queue.sqs.message.SqsMessageService;
import com.br.poc.kafka.consumer.dto.MessageDto;
import io.awspring.cloud.dynamodb.DynamoDbTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageConsumerController {

    private final KafkaTemplate<String, MessageDto> kafkaTemplate;
    private final DynamoDbTemplate dynamoDbTemplate;
    private final SqsMessageService sqsMessageService;

    public KafkaMessageConsumerController(KafkaTemplate<String, MessageDto> kafkaTemplate, DynamoDbTemplate dynamoDbTemplate, SqsMessageService sqsMessageService) {
        this.kafkaTemplate = kafkaTemplate;
        this.dynamoDbTemplate = dynamoDbTemplate;
        this.sqsMessageService = sqsMessageService;
    }

    @KafkaListener(topics = "poc-kafka-consumer", groupId = "poc-group", containerFactory = "kafkaMessageListener")
    public void consumer(MessageDto messageDto) {
        System.out.println("chegou aqui");
        this.kafkaTemplate.send("poc-sqs-consumer", messageDto);

        var messagesEntity = MessagesEntity.fromMessage(messageDto);

        System.out.println("Pesisting in dynamoDB");
        dynamoDbTemplate.save(messagesEntity);

        System.out.println("Sending message to SQS");
        sqsMessageService.sendMessage(messageDto);
    }
}
