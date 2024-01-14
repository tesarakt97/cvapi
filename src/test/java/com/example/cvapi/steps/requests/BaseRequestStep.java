package com.example.cvapi.steps.requests;

import com.example.cvapi.data.enums.StatusCode;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import static io.restassured.RestAssured.given;

@Slf4j
public abstract class BaseRequestStep {

    @Step("Выполнить GET запрос по адресу {endPoint}")
    protected String doGetRequest(String endPoint, StatusCode statusCode, RequestSpecification requestSpec) {
        JsonPath jsonPath = new JsonPath(
                given().log().uri()
                        .spec(requestSpec)
                        .when()
                        .get(endPoint)
                        .then()
                        .statusCode(statusCode.getStatusCode())
                        .extract().asString()
        );
        return jsonPath.prettify();
    }

    @Step("Выполнить GET запрос по адресу {endPoint}")
    protected String doGetRequestWithParam(String endPoint, Map<String, String> pathParams, StatusCode statusCode,
                                           RequestSpecification requestSpec) {
        JsonPath jsonPath = new JsonPath(
                given().log().uri()
                        .spec(requestSpec)
                        .when()
                        .pathParams(pathParams)
                        .get(endPoint)
                        .then()
                        .statusCode(statusCode.getStatusCode())
                        .extract().asString()
        );
        return jsonPath.prettify();
    }

    protected String doGetRequestWithQueryParam(String endPoint, Map<String, String> pathParams, StatusCode statusCode,
                                           RequestSpecification requestSpec) {
        JsonPath jsonPath = new JsonPath(
                given().log().uri()
                        .spec(requestSpec)
                        .when()
                        .queryParams(pathParams)
                        .get(endPoint)
                        .then()
                        .statusCode(statusCode.getStatusCode())
                        .extract().asString()
        );
        return jsonPath.prettify();
    }
}
