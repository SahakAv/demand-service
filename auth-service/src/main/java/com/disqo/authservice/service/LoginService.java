package com.disqo.authservice.service;

import com.disqo.authservice.client.LoginServiceClient;
import com.disqo.authservice.client.response.UserResponse;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class LoginService implements UserDetailsService {

    private final LoginServiceClient loginServiceClient;

    public LoginService(LoginServiceClient loginServiceClient) {
        this.loginServiceClient = loginServiceClient;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserResponse userResponse = loginServiceClient.getByUsername(username);
        SimpleGrantedAuthority authRole = new SimpleGrantedAuthority(userResponse.getType().toString());
        return new User(username,
                userResponse.getPassword(), true, true, true,
                true, Collections.singletonList(authRole));
    }
}
