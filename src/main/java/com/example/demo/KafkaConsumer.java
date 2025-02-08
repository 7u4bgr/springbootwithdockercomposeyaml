package com.example.demo;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "mesajlar", groupId = "mesaj-grubu")
    public void consume(ConsumerRecord<String, String> record) {
        System.out.println("Mesaj alındı: " + record.value());
    }
}