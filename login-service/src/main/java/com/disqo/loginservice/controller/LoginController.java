package com.disqo.loginservice.controller;

import com.disqo.loginservice.model.LoginRequest;
import com.disqo.loginservice.model.SignUpUserRequest;
import com.disqo.loginservice.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping(value = "/auth")
//    @ApiOperation(value = "This is login api", notes = "Request contains username and password")
    public String loginUser(@RequestBody LoginRequest loginRequest) {
        return loginService.login(loginRequest);
    }

    @PostMapping(value = "/signup")
//    @ApiOperation(value = "This is login api", notes = "Request contains username and password")
    public ResponseEntity signUpUser(@RequestBody SignUpUserRequest signUpUserRequest) {
        log.info("Requested to register user {} with type {}", signUpUserRequest.getUsername(), signUpUserRequest.getUserType());
        loginService.signUp(signUpUserRequest);
        log.info("User {} successfully signup", signUpUserRequest.getUsername());
        return ResponseEntity.accepted().build();
    }

}
