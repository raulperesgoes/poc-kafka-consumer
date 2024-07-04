package com.br.poc.kafka.consumer.controller.api.restfull.message;


import com.br.poc.kafka.consumer.domain.service.kafka.producer.KafkaMessageProducerService;
import com.br.poc.kafka.consumer.domain.model.entity.MessagesEntity;
import com.br.poc.kafka.consumer.dto.MessageDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1")
public class MessageController {

    private final KafkaMessageProducerService kafkaProducerController;

    public MessageController (KafkaMessageProducerService kafkaProducerController) {
        this.kafkaProducerController = kafkaProducerController;
    }

    @PostMapping("/messages")
    public ResponseEntity<Void> save(@RequestBody MessageDto messageDto) {
        System.out.println("Postando mensagem no tópico");
        var message = MessagesEntity.fromMessage(messageDto);
        kafkaProducerController.producer(messageDto);
        return ResponseEntity.ok().build();
    }
}
