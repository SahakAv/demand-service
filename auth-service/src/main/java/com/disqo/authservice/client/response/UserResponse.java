package com.disqo.authservice.client.response;

import lombok.Data;

@Data
public class UserResponse {
    private String username;
    private String password;
    private UserType type;


}