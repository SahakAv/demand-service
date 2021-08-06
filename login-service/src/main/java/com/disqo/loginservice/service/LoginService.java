package com.disqo.loginservice.service;

import com.disqo.loginservice.model.LoginRequest;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {

     String login(LoginRequest loginRequest, HttpServletRequest request);

}
