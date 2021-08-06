package com.disqo.providerservice.model.response;

import com.disqo.providerservice.model.domain.ServiceProvider;
import lombok.Data;

@Data
public class ServiceProviderResponse {
    private String providerName;
    private String serviceType;


    public ServiceProviderResponse(ServiceProvider serviceProvider) {
        providerName = serviceProvider.getName();
        serviceType = serviceProvider.getServiceType().getServiceName();
    }
}
