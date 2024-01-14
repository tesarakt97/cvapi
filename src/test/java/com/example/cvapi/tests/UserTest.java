package com.example.cvapi.tests;

import com.example.cvapi.data.constant.UserConstant;
import com.example.cvapi.data.dto.UserDTO;
import com.example.cvapi.steps.assertions.UserAssertionStep;
import com.example.cvapi.steps.requests.UsersRequestStep;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DisplayName("Пользователи")
@Tag("users")
public class UserTest extends BaseTest {

    @Autowired
    private UsersRequestStep personsRequestStep;

    @Autowired
    private UserAssertionStep userAssertionStep;

    @Autowired
    private UserConstant userConstant;

    @Test
    @DisplayName("Получение пользователя по id")
    void getPersonTest() {
        UserDTO testUser = personsRequestStep.getUserById(1, requestSpec);
        userAssertionStep.assertUser(userConstant.getUserForAssert(), testUser);
    }

    @Test
    @DisplayName("Получение списка пользователей")
    void getPersonsTest() {
        List<UserDTO> testUsers = personsRequestStep.getUsersShortList(requestSpec);
        userAssertionStep.assertUsers(userConstant.getUsersForAssert(), testUsers);
    }
}
