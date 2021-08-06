package com.disqo.authservice.client;

import com.disqo.authservice.client.response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.disqo.authservice.utils.Constants.LoginMicroServiceConstants.GET_BY_USERNAME;

@FeignClient(name = "login-service", url = "${login.service.url}")
@Service
public interface LoginServiceClient {

    @RequestMapping(method = RequestMethod.GET, value = GET_BY_USERNAME)
    UserResponse getByUsername(@PathVariable String username);
}
