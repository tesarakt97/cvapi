package com.example.cvapi.steps.requests;

import com.example.cvapi.config.RequestSpec;
import com.example.cvapi.data.dto.UserDTO;
import com.example.cvapi.data.endPoints.UserEndPoints;
import com.example.cvapi.data.enums.StatusCode;
import com.example.cvapi.utils.JsonHelper;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UsersRequestStep extends BaseRequestStep {

    @Autowired
    UserEndPoints userEndPoints;

    @Autowired
    JsonHelper<UserDTO> jsonHelper;

    @Step("Получить пользователя по {id}")
    public UserDTO getUserById(int userId, RequestSpec requestSpec) {
        Map<String, String> param = new HashMap<>();
        param.put("id", String.valueOf(userId));
        return jsonHelper.transFormToObject(
                doGetRequestWithParam(userEndPoints.GET_USER_BY_ID, param, StatusCode.OK,
                        requestSpec.requestSpecification), UserDTO.class);
    }

    @Step("Получить всех пользователей")
    public List<UserDTO> getUsers(RequestSpec requestSpec) {
        return jsonHelper.transFormToListObject(
                doGetRequest(userEndPoints.GET_USERS, StatusCode.OK,
                        requestSpec.requestSpecification), UserDTO.class);
    }

    @Step("Получить всех пользователей (короткий список 4 пользователя)")
    //Получение для короткого списка (первые 4) из-за того что список очень длинный
    public List<UserDTO> getUsersShortList(RequestSpec requestSpec) {
        Map<String, String> param = new HashMap<>();
        param.put("limit", String.valueOf(4));
        return jsonHelper.transFormToListObject(
                doGetRequestWithQueryParam(userEndPoints.USERS, param, StatusCode.OK,
                        requestSpec.requestSpecification), UserDTO.class);
    }
}
