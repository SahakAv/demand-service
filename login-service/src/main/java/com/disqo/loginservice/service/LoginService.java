package com.disqo.loginservice.service;

import com.disqo.loginservice.model.LoginRequest;
import com.disqo.loginservice.model.SignUpUserRequest;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {

     String login(LoginRequest loginRequest);

     void signUp(SignUpUserRequest signUpUserRequest);

}
