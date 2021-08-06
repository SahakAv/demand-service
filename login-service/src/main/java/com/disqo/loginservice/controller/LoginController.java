package com.disqo.loginservice.controller;

import com.disqo.loginservice.model.LoginRequest;
import com.disqo.loginservice.model.SignUpUserRequest;
import com.disqo.loginservice.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
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
         loginService.signUp(signUpUserRequest);
         return ResponseEntity.accepted().build();
    }

}
