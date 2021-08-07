package com.disqo.customerservice.service;

import com.disqo.customerservice.model.payload.request.CreateServiceRequest;
import com.disqo.customerservice.model.payload.response.ServiceRequest;

import java.util.List;

public interface CustomerService {


    ServiceRequest createServiceRequest(CreateServiceRequest createServiceRequest, String username);

    List<ServiceRequest> getCustomerRequests(String username);
}
