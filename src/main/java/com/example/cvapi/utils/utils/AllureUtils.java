package com.example.cvapi.utils.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Attachment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Класс для работы с инструментами Allure.
 */
public class AllureUtils {

    private static final String resourcePath = "src/test/resources";

    @Attachment(value = "Прикрепленный txt файл", type = "text/plain", fileExtension = ".txt")
    public static byte[] attachTxtFile(String filePath) {
        try {
            return Files.readAllBytes(Paths.get(resourcePath, filePath));
        } catch (IOException e) {
            throw new RuntimeException("Ошибка прикрепления файла к отчету: Не удалось прочитать файл: " + filePath, e);
        }
    }

    @Attachment(value = "{0}", type = "application/json", fileExtension = ".json")
    public static byte[] attachJsonFile(String filePath) {
        try {
            return Files.readAllBytes(Paths.get(resourcePath, filePath));
        } catch (IOException e) {
            throw new RuntimeException("Ошибка прикрепления файла к отчету: Не удалось прочитать файл: " + filePath, e);
        }
    }

    @Attachment(value = "Ответ сервиса", type = "application/json")
    public static String attachResponse(Object response) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
        } catch (Exception e) {
            return "Failed to serialize response: " + e.getMessage();
        }
    }

    @Attachment(value = "{attachName}", type = "application/json")
    public static String attachResponseWithName(String attachName, Object response) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
        } catch (Exception e) {
            return "Failed to serialize response: " + e.getMessage();
        }
    }

}
