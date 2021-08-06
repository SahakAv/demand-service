package com.disqo.customerservice.utils;

public class Constants {


    public interface RequestMicroServiceConstants {
        String SERVICE_NAME = "request-service";
        String CREATE_REQUEST_URL = "/request";
    }

    public interface ServiceProviderMicroServiceConstants {
        String SERVICE_NAME = "provider-service";
        String GET_SERVICE_TYPE_BY_NAME = "/service-type/{name}";
        String GET_SERVICE_PROVIDER_BY_NAME = "/provider/name/{name}";

    }
}
