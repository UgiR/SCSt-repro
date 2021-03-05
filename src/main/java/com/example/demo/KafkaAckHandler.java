package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.FluxMessageChannel;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

@Configuration
public class KafkaAckHandler {

    @Bean
    MessageChannel kafkaAckListener() {
        return new FluxMessageChannel();
    }

    @ServiceActivator(inputChannel = "kafkaAckListener")
    void onKafkaAck(final Message<?> message) {
        System.out.println("Got Kafka producer acknowledgment");
    }

}
