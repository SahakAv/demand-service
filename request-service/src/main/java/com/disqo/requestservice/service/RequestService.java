package com.disqo.requestservice.service;

import com.disqo.requestservice.model.domain.ServiceRequest;
import com.disqo.requestservice.model.request.CreateServiceRequest;

public interface RequestService {

    ServiceRequest create(CreateServiceRequest request, String username);


}
