package com.disqo.requestservice.service;

import com.disqo.requestservice.client.UserServiceClient;
import com.disqo.requestservice.model.domain.ServiceRequest;
import com.disqo.requestservice.model.request.CreateServiceRequest;
import com.disqo.requestservice.model.response.UserResponse;
import com.disqo.requestservice.repository.ServiceRequestRepository;
import org.springframework.stereotype.Service;

@Service
public class RequestServiceImpl implements RequestService {

    private final ServiceRequestRepository requestRepository;

    private final UserServiceClient userServiceClient;

    public RequestServiceImpl(ServiceRequestRepository requestRepository, UserServiceClient userServiceClient) {
        this.requestRepository = requestRepository;
        this.userServiceClient = userServiceClient;
    }


    @Override
    public ServiceRequest create(CreateServiceRequest request, String username) {
        UserResponse user = userServiceClient.getByUserName(username);
        ServiceRequest serviceRequest = new ServiceRequest(request, user.getUsername());
        return requestRepository.save(serviceRequest);
    }
}
