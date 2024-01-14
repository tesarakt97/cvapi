package com.example.cvapi.tests;

import com.example.cvapi.config.RequestSpec;
import com.example.cvapi.env.Environment;
import io.qameta.allure.Epic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Epic("Тестовый проект для CV")
public class BaseTest {

    @Autowired
    public Environment environment;

    @Autowired
    public RequestSpec requestSpec;
}
