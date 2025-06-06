package com.revisao.demo.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.revisao.demo.kafka.dto.ProcessEventDTO;
import com.revisao.demo.service.KernelService;

@Component
public class ProcessEventConsumer {

    private final KernelService kernelService;

    public ProcessEventConsumer(KernelService kernelService) {
	this.kernelService = kernelService;
    }

    @KafkaListener(topics = "process-events", groupId = "process-consumer-group", containerFactory = "kafkaListenerContainerFactory")
    public void consume(ProcessEventDTO event) {
	System.out.println("🟢 Evento recebido do Kafka:");
	System.out.println("App ID: " + event.getAppId());
	System.out.println("Payload :" + event.getPayload());
	System.out.println("Type :" + event.getType());

	switch (event.getType()) {
	case "OPEN_APP":
	    kernelService.openApp(event.getAppId(), event.getPayload(), event.getType());
	    break;
	case "CLOSE_APP":
	    kernelService.closeApp(event.getAppId(), event.getPayload(), event.getType());
	    break;
	case "SAVE_FILE":
	    kernelService.saveFile(event.getAppId(), event.getPayload(), event.getType());
	    break;
	}

    }
}