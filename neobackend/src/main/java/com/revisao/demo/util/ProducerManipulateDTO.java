package com.revisao.demo.util;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.revisao.demo.kafka.dto.ProcessEventDTO;
import com.revisao.demo.kafka.producer.ProcessEventProducer;

@Service
public class ProducerManipulateDTO {

    private final ProcessEventProducer producer;

    public ProducerManipulateDTO(ProcessEventProducer producer) {
	this.producer = producer;

    }

    public void createProcessDTO(String appId, Map<String, Object> payload, String type) {
	ProcessEventDTO processEvent = new ProcessEventDTO();
	processEvent.setAppId(appId);
	processEvent.setPayload(payload);
	processEvent.setType(type);
	producer.sendProcessEvent(processEvent);
    }
}
