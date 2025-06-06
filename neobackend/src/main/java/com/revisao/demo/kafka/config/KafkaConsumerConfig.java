package com.revisao.demo.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.revisao.demo.kafka.dto.ProcessEventDTO;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Bean
    public ConsumerFactory<String, ProcessEventDTO> consumerFactory() {
	JsonDeserializer<ProcessEventDTO> deserializer = new JsonDeserializer<>(ProcessEventDTO.class);
	deserializer.setRemoveTypeHeaders(false);
	deserializer.addTrustedPackages("*");
	deserializer.setUseTypeMapperForKey(true);

	Map<String, Object> props = new HashMap<>();
	props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092");
	props.put(ConsumerConfig.GROUP_ID_CONFIG, "process-consumer-group");
	props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);

	return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ProcessEventDTO> kafkaListenerContainerFactory() {
	ConcurrentKafkaListenerContainerFactory<String, ProcessEventDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
	factory.setConsumerFactory(consumerFactory());
	return factory;
    }
}
