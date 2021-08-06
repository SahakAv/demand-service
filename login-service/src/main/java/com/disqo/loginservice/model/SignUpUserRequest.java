package com.disqo.loginservice.model;

import lombok.Data;

@Data
public class SignUpUserRequest {
    private String username;
    private String password;
    private UserType userType;
}
