package com.example.hongber.common.util.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> String toJson(T object) {
        String jsonString = null;

        try {
            jsonString = objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            log.error("toJson() - toJsonPretty - Cannot convert to JSON", e);
        }

        return jsonString;
    }

    public static <T> String toJsonPretty(T object) {
        String jsonString = null;

        try {
            jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (Exception e) {
            log.error("toJsonPretty() - Cannot convert to JSON", e);
        }

        return jsonString;
    }
}
