package com.example.cvapi.utils.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.apache.commons.io.IOUtils.resourceToString;
import static ru.vtb.retail.loan.migr.processor.utils.AllureUtils.attachJsonFile;
import static ru.vtb.retail.loan.migr.processor.utils.JsonUtils.readFromStringWithDeserialize;
import static ru.vtb.retail.loan.migr.processor.utils.JsonUtils.readListFromStringDeserialize;

/**
 * Класс для работы с файлами.
 */
@Slf4j
public class FileUtils {

    @Step("Преобразовать файл в строчное значение")
    public static String convertFileToString(String filePath) {
        if (filePath != null) {
            attachJsonFile(filePath);
        }
        try {
            return resourceToString(filePath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Step("Прочитать файл и десериализовать его")
    public static <T> T readFromFileWithDeserialize(String filePath, Class<T> clazz) {
        return readFromStringWithDeserialize(convertFileToString(filePath), clazz);
    }

    @Step("Изменить значения в полученном файле")
    public static String changeValueInFile(String filePath, Map<String, Object> newValues) {
        String convertedStringFile = convertFileToString(filePath);
        DocumentContext context = JsonPath.parse(convertedStringFile, Configuration.defaultConfiguration());
        Objects.requireNonNull(context);
        if (newValues != null) {
            newValues.forEach((x$0, x$1) -> {
                try {
                    context.set(x$0, x$1);
                } catch (PathNotFoundException e) {
                    log.error("Не найден jsonPath путь: " + x$0);
                    System.out.println("Не найден jsonPath путь: " + x$0);
                    throw new PathNotFoundException(e);
                }
            });
        }
        return context.jsonString();
    }

    @Step("Удалить значение из json")
    public static String removeValueInJson(String json, String... paths) {
        DocumentContext context = JsonPath.parse(json, Configuration.defaultConfiguration());
        Objects.requireNonNull(context);
        Arrays.stream(paths).forEach(context::delete);
        return context.jsonString();
    }

    @Step("Прочитать файл и десериализовать его с заменой значений внутри файла")
    public static <T> T readFromFileWithDeserializeWithReplace(String filePath, Map<String, Object> replaces, Class<T> clazz) {
        return readFromStringWithDeserialize(changeValueInFile(filePath, replaces), clazz);
    }

    @Step("Прочитать файл и десериализовать его (список)")
    public static <T> List<T> readListFromFileWithDeserialize(String filePath, TypeReference<List<T>> typeReference) {
        return readListFromStringDeserialize(convertFileToString(filePath), typeReference);
    }
}

