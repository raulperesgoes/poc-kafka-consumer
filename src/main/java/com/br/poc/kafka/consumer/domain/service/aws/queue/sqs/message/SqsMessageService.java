package com.br.poc.kafka.consumer.domain.service.aws.queue.sqs.message;

import com.br.poc.kafka.consumer.domain.model.entity.MessagesEntity;
import com.br.poc.kafka.consumer.dto.MessageDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;

import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

import java.lang.reflect.Field;

@Service
public class SqsMessageService {

    private final SqsClient sqsClient;

    public SqsMessageService(SqsClient sqsClient) {
        this.sqsClient = sqsClient;
    }

    public void sendMessage(MessageDto message) {

        SendMessageRequest sendMessageRequest = SendMessageRequest.builder()
                .queueUrl("")
                .messageBody(convertRecordToStringJson(message))
                .build();

        sqsClient.sendMessage(sendMessageRequest);
    }

    private String convertRecordToStringJson(MessageDto message) {
        String messageBody = "";
        MessagesEntity messagesEntity = new MessagesEntity(message);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
                messageBody = objectMapper.writeValueAsString(messagesEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return messageBody;
    }
}
