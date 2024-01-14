package com.example.cvapi.data.endPoints;

import org.springframework.stereotype.Component;

@Component
public final class UserEndPoints extends EndPointsBase {

    //persons
    public final String USERS = "users";
    public final String GET_USERS = USERS+SLASH;
    public final String GET_USER_BY_ID = USERS+SLASH+PARAM_ID;
}
