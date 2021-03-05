package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.converter.AbstractMessageConverter;

public class ThingConverter extends AbstractMessageConverter {

    @Override
    protected boolean supports(Class<?> aClass) {
        return Thing.class.isAssignableFrom(aClass);
    }

    @Override
    protected Object convertToInternal(Object payload, MessageHeaders headers, Object conversionHint) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(payload).getBytes();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }
}