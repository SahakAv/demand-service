package com.disqo.customerservice.service;

import com.disqo.customerservice.model.payload.request.CreateServiceRequest;
import com.disqo.customerservice.model.payload.response.ServiceRequest;

public interface CustomerService {


     ServiceRequest createServiceRequest(CreateServiceRequest createServiceRequest);
}
