package com.disqo.requestservice.service;

import com.disqo.requestservice.model.domain.ServiceRequest;
import com.disqo.requestservice.model.request.CreateServiceRequest;
import com.disqo.requestservice.model.request.ServiceProviders;

import java.util.List;

public interface RequestService {

    ServiceRequest create(CreateServiceRequest request, String username);

    ServiceRequest getById(Long id);

    List<ServiceRequest> getConsumerRequests(String username);

    List<ServiceRequest> getNewRequests(List<String> serviceTypes);

    ServiceRequest assignToProvider(Long id, String providerName);

    ServiceRequest close(Long id);

    List<ServiceRequest> getProvidersAssignedRequests(ServiceProviders serviceProviders);
}
