package com.example.demo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final KafkaMessageProducer kafkaProducer;

    public MessageController(KafkaMessageProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping("/send")
    public String sendMessage(@RequestParam String message) {
        kafkaProducer.sendMessage("mesajlar", message);
        return "Mesaj gönderildi: " + message;
    }
}
