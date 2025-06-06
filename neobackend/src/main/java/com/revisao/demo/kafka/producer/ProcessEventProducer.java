package com.revisao.demo.kafka.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.revisao.demo.kafka.dto.ProcessEventDTO;

@Service
public class ProcessEventProducer {

    private final KafkaTemplate<String, ProcessEventDTO> kafkaTemplate;

    @Value("${topic.process-events}")
    private String topic;

    public ProcessEventProducer(KafkaTemplate<String, ProcessEventDTO> kafkaTemplate) {
	this.kafkaTemplate = kafkaTemplate;
    }

    public void sendProcessEvent(ProcessEventDTO event) {
	kafkaTemplate.send(topic, event);
	System.out.println("Evento Kafka enviado: " + event);
    }
}