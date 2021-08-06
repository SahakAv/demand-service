package com.disqo.providerservice.utils;

public class Constants {

    public interface RequestMicroServiceConstants {
        String SERVICE_NAME = "request-service";
        String GET_NEW_REQUESTS = "/request/new";
        String GET_BY_ID = "/request/{requestId}";
        String ASSIGN_TO_PROVIDER = "/request/assign/{requestId}/{providerName}";
        String CLOSE_REQUEST = "/request/close/{requestId}";
        String REQUESTS_BY_SERVICE_PROVIDERS = "/request/provider";
    }
}
