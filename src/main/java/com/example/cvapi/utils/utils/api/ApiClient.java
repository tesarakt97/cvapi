package com.example.cvapi.utils.utils.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiClient {
    private String baseUrl;

    public ApiClient(String baseUrl) {
        this.baseUrl = baseUrl;
        RestAssured.baseURI = baseUrl;
    }

    // Метод для выполнения GET запроса
    public Response get(String endpoint) {
        return RestAssured.given()
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();
    }

    // Метод для выполнения POST запроса
    public Response post(String endpoint, int statusCode, Object body) {
        return RestAssured.given()
                .contentType("application/json") // Укажите тип контента
                .body(body) // Укажите тело запроса
                .when()
                .post(endpoint)
                .then()
                .statusCode(statusCode)
                .extract()
                .response();
    }
}
