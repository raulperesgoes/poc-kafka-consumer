package com.br.poc.kafka.consumer.domain.service.aws.queue.sqs.message;

import com.br.poc.kafka.consumer.dto.MessageDto;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;

import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@Service
public class SqsMessageService {

    private final SqsClient sqsClient;

    public SqsMessageService(SqsClient sqsClient) {
        this.sqsClient = sqsClient;
    }

    public void sendMessage(MessageDto message) {

        SendMessageRequest sendMessageRequest = SendMessageRequest.builder()
                .queueUrl("")
                .messageBody("enviando mensagem")
                .build();

        sqsClient.sendMessage(sendMessageRequest);
    }

    /*private Map<String, MessageAttributeValue> convertRecordToMap(MessageDto message) {
        Map<String, MessageAttributeValue> messageAttributes = new HashMap<>();

        for (var component : message.getClass().getRecordComponents()) {
            try {
                var value = component.getAccessor().invoke(message);
                System.out.println(component.getName() + ": " + value);

                messageAttributes.put("key", MessageAttributeValue()
                        .withDataType("String")
                        .withStringValue(value));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return messageAttributes;
    }*/
}
