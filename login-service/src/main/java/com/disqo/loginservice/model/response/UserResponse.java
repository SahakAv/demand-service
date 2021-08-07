package com.disqo.loginservice.model.response;

import com.disqo.loginservice.model.User;
import com.disqo.loginservice.model.UserType;
import lombok.Data;

@Data
public class UserResponse {
    private String username;
    private String password;
    private UserType type;

    public UserResponse(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.type = user.getType();
    }
}
