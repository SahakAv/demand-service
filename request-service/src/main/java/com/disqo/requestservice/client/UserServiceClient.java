package com.disqo.requestservice.client;

import com.disqo.requestservice.model.response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.disqo.requestservice.utils.Constants.LoginMicroServiceConstants.SERVICE_NAME;
import static com.disqo.requestservice.utils.Constants.LoginMicroServiceConstants.GET_USER_BY_USERNAME;

@Service
@FeignClient(name = SERVICE_NAME, url = "${login.service.url}")
public interface UserServiceClient {

    @RequestMapping(method = RequestMethod.GET, value = GET_USER_BY_USERNAME)
    UserResponse getByUserName(@PathVariable String username);
}
