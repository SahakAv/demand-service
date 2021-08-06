package com.disqo.loginservice.service.serviceImpl;

import com.disqo.loginservice.model.LoginRequest;
import com.disqo.loginservice.model.User;
import com.disqo.loginservice.repo.UserRepository;
import com.disqo.loginservice.service.JwtService;
import com.disqo.loginservice.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;

@Service
public class LoginServiceImpl implements LoginService {


    private final JwtService jwtService;

    private final UserRepository userRepository;


    public LoginServiceImpl(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    public String login(LoginRequest loginRequest, HttpServletRequest request) {
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                        INVALID_CREDENTIALS));
        validatePassword(loginRequest, user);
        return jwtService.generateJwtToken(user);
    }


    private void validatePassword(LoginRequest loginRequest, User user) {
        if (!BCrypt.checkpw(loginRequest.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, INVALID_CREDENTIALS);
        }
    }


    private static String INVALID_CREDENTIALS = "Please provider valid credentials";

}
