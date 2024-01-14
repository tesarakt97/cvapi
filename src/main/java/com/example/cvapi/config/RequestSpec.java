package com.example.cvapi.config;

import com.example.cvapi.env.Environment;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RequestSpec {

    @Autowired
    Environment environment;


    public RequestSpecification requestSpecification;

    RequestSpec(Environment environment) {
        this.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(environment.baseUrl)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.BODY)
                .build();
    }

}
