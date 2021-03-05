package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.function.Supplier;

@Component
public class ThingSupplier implements Supplier<Flux<Message<Thing>>> {

    @SuppressWarnings("deprecation")
    EmitterProcessor<Message<Thing>> emitterProcessor = EmitterProcessor.create();
    FluxSink<Message<Thing>> fluxSink = emitterProcessor.sink();

    void send(Thing thing) {
        Message<Thing> thingMessage = MessageBuilder.withPayload(thing).build();
        fluxSink.next(thingMessage);
    }

    @Override
    public Flux<Message<Thing>> get() {
        return emitterProcessor;
    }
}
