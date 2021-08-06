package com.disqo.loginservice.service.serviceImpl;

import com.disqo.loginservice.model.LoginRequest;
import com.disqo.loginservice.model.SignUpUserRequest;
import com.disqo.loginservice.model.User;
import com.disqo.loginservice.repo.UserRepository;
import com.disqo.loginservice.service.JwtService;
import com.disqo.loginservice.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {


    private final JwtService jwtService;

    private final UserRepository userRepository;


    public LoginServiceImpl(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    public String login(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                        INVALID_CREDENTIALS));
        validatePassword(loginRequest, user);
        return jwtService.generateJwtToken(user);
    }

    @Override
    public void signUp(SignUpUserRequest signUpUserRequest) {
        Optional<User> user = userRepository.findByUsername(signUpUserRequest.getUsername());
        if (user.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    INVALID_USERNAME);
        }
        userRepository.save(User.builder()
                .type(signUpUserRequest.getUserType()).username(signUpUserRequest.getUsername())
                .password(BCrypt.hashpw(signUpUserRequest.getPassword(), BCrypt.gensalt())).build());
    }


    private void validatePassword(LoginRequest loginRequest, User user) {
        if (!BCrypt.checkpw(loginRequest.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, INVALID_CREDENTIALS);
        }
    }


    private static final String INVALID_CREDENTIALS = "Please provide valid credentials";
    private static final String INVALID_USERNAME = "User by this username is already registered";

}
