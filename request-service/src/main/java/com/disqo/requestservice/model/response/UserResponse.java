package com.disqo.requestservice.model.response;

import lombok.Data;

@Data
public class UserResponse {
    private String username;
    private String password;
    private UserType type;


}
