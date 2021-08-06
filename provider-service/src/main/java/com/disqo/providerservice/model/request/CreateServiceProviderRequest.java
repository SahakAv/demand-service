package com.disqo.providerservice.model.request;

import lombok.Data;

@Data
public class CreateServiceProviderRequest {
    private String serviceTypeName;
    private String serviceProviderName;
    private String username;
    private String password;
}
