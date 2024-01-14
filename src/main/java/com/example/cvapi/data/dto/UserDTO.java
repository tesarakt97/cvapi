package com.example.cvapi.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO extends BaseDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String countryCode;
    private int companyId;
}
