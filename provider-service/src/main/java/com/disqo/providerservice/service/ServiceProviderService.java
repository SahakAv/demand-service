package com.disqo.providerservice.service;

import com.disqo.providerservice.model.domain.ServiceProvider;
import com.disqo.providerservice.model.request.CreateServiceProviderRequest;
import com.disqo.providerservice.model.response.ServiceProviderResponse;

import java.util.List;

public interface ServiceProviderService {

    ServiceProvider create(CreateServiceProviderRequest request, String owner);

    List<ServiceProviderResponse> getAllByServiceType(String serviceType);

    ServiceProviderResponse getByName(String name);

    List<ServiceProviderResponse> getAll();
}