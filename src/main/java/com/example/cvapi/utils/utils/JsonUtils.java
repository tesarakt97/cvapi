package com.example.cvapi.utils.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.qameta.allure.Step;

import java.util.List;

public class JsonUtils {

    public static final ObjectMapper jsonUtilsMapper = new ObjectMapper();

    static {
        jsonUtilsMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        jsonUtilsMapper.registerModule(new JavaTimeModule());
    }

    @Step("Сериализовать объект в строку")
    public static String serializeObjectToString(Object object) {
        try {
            return jsonUtilsMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Step("Десериализовать строки")
    public static <T> T readFromStringWithDeserialize(String stringToDeserialize, Class<T> clazz) {
        try {
            return jsonUtilsMapper.readValue(stringToDeserialize, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Step("Десериализовать список")
    public static <T> List<T> readListFromStringDeserialize(String stringListToDeserialize, TypeReference<List<T>> typeReference) {
        try {
            return jsonUtilsMapper.readValue(stringListToDeserialize, typeReference);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
