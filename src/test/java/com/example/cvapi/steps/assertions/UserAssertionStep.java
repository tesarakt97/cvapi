package com.example.cvapi.steps.assertions;

import com.example.cvapi.data.dto.UserDTO;
import io.qameta.allure.Step;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserAssertionStep extends BaseAssertionStep {

    @Step("Проверка пользователя")
    public void assertUser(UserDTO baseUser, UserDTO responseUser) {
        SoftAssertions.assertSoftly(softly -> {
                    Assertions.assertThat(baseUser.getId()).isEqualTo(responseUser.getId());
                    Assertions.assertThat(baseUser.getFirstName()).isEqualTo(responseUser.getFirstName());
                    Assertions.assertThat(baseUser.getLastName()).isEqualTo(responseUser.getLastName());
                    Assertions.assertThat(baseUser.getCountryCode()).isEqualTo(responseUser.getCountryCode());
                    Assertions.assertThat(baseUser.getCompanyId()).isEqualTo(responseUser.getCompanyId());
                }
        );
    }

    @Step("Проверка списка пользователей")
    public void assertUsers(List<UserDTO> baseUsers, List<UserDTO> responseUsers) {
        Assertions.assertThat(baseUsers.size()).isEqualTo(responseUsers.size());
        for (UserDTO baseUser : baseUsers) {
            assertUser(baseUser, responseUsers.stream()
                    .filter(x -> x.getId() == baseUser.getId())
                    .findFirst()
                    .get());
        }
    }

}
