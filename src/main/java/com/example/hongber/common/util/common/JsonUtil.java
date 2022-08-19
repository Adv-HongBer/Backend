package com.example.hongber.common.util.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
public class JsonUtil {
   private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String toJson(Object object) {
        String jsonString = null;

        try {
            jsonString = objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            log.error("toJson() - toJsonPretty - Cannot convert to JSON", e);
        }

        return jsonString;
    }

    public static String toJsonPretty(Object object) {
        String jsonString = null;

        try {
            jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (Exception e) {
            log.error("toJsonPretty() - Cannot convert to JSON", e);
        }

        return jsonString;
    }
}
