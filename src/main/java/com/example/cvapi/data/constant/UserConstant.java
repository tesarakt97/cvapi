package com.example.cvapi.data.constant;

import com.example.cvapi.data.dto.UserDTO;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Component
public class UserConstant {

    UserDTO userForAssert = new UserDTO(1, "Jerry", "Parker", "CF", 45);

    List<UserDTO> usersForAssert = new ArrayList<>(Arrays.asList(
            new UserDTO(3, "Noah", "Hernandez", "IS", 25),
            new UserDTO(1, "Jerry", "Parker", "CF", 45),
            new UserDTO(4, "Jeremy", "White", "GT", 21),
            new UserDTO(2, "Ryan", "Hughes", "PR", 48)
    ));

}
