package com.github.RakhmedovRS.kasa.kp115.transformer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.RakhmedovRS.kasa.kp115.pojo.EmeterResponseJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmeterResponseJsonTransformer {
    private final ObjectMapper objectMapper;

    public EmeterResponseJsonTransformer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public EmeterResponseJson transform(String json) throws Exception {
        log.debug("Attempting to transform json {}", json);
        return objectMapper.readValue(json, EmeterResponseJson.class);
    }
}
