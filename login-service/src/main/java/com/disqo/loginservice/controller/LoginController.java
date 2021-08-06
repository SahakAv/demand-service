package com.disqo.loginservice.controller;

import com.disqo.loginservice.model.LoginRequest;
import com.disqo.loginservice.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping(value = "/login")
//    @ApiOperation(value = "This is login api", notes = "Request contains username and password")
    public String loginUser(@RequestBody LoginRequest requestDTO, HttpServletRequest request) {
        return loginService.login(requestDTO, request);
    }


    @PostMapping(value = "/hello")
//    @ApiOperation(value = "This is login api", notes = "Request contains username and password")
    public String loginUser() {
        return "asdasdasd";
    }

}
