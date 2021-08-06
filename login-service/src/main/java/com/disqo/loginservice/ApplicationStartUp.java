package com.disqo.loginservice;

import com.disqo.loginservice.model.User;
import com.disqo.loginservice.model.UserType;
import com.disqo.loginservice.repo.UserRepository;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;



@Component
public class ApplicationStartUp {

    private final UserRepository userRepository;

    public ApplicationStartUp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @EventListener(ApplicationStartedEvent.class)
    public void loadData() {
        if (ObjectUtils.isEmpty(userRepository.findAll())) {
            userRepository.save(User.builder()
                    .type(UserType.SERVICE_PROVIDER).username("admin")
                    .password(BCrypt.hashpw("admin", BCrypt.gensalt())).build());
            userRepository.save(User.builder()
                    .type(UserType.CUSTOMER).username("user")
                    .password(BCrypt.hashpw("user", BCrypt.gensalt())).build());
        }
    }

}


