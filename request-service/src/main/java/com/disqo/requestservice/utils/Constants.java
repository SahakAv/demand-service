package com.disqo.requestservice.utils;

public class Constants {

    public interface ServiceProviderMicroServiceConstants {
        String SERVICE_NAME = "service-provider";
        String GET_SERVICE_TYPE_BY_NAME = "/service-type/{name}";
    }

    public interface LoginMicroServiceConstants {
        String SERVICE_NAME = "login-service";
        String GET_USER_BY_USERNAME = "/user/{username}";
    }
}
