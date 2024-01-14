package com.example.cvapi.env;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Environment {


    @Value("${api.baseUrl}")
    public String baseUrl;

}
