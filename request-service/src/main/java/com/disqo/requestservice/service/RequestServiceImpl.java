package com.disqo.requestservice.service;

import com.disqo.requestservice.client.UserServiceClient;
import com.disqo.requestservice.model.domain.RequestStatus;
import com.disqo.requestservice.model.domain.ServiceRequest;
import com.disqo.requestservice.model.request.CreateServiceRequest;
import com.disqo.requestservice.model.request.ServiceProviders;
import com.disqo.requestservice.model.response.UserResponse;
import com.disqo.requestservice.repository.ServiceRequestRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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

    @Override
    public ServiceRequest getById(Long id) {
        return get(id);
    }

    @Override
    public List<ServiceRequest> getConsumerRequests(String username) {
        return requestRepository.findByOwner(username);
    }

    @Override
    public List<ServiceRequest> getNewRequests(List<String> serviceTypes) {
        return requestRepository.findRequestByTypeAndStatus(serviceTypes, RequestStatus.NEW);
    }

    @Override
    public ServiceRequest assignToProvider(Long id, String providerName) {
        ServiceRequest serviceRequest = get(id);
        serviceRequest.setStatus(RequestStatus.ASSIGNED_TO_PROVIDER);
        serviceRequest.setAssigned(providerName);
        return requestRepository.save(serviceRequest);
    }

    @Override
    public ServiceRequest close(Long id) {
        ServiceRequest serviceRequest = get(id);
        serviceRequest.setStatus(RequestStatus.CLOSED);
        requestRepository.save(serviceRequest);
        return getById(id);
    }


    @Override
    public List<ServiceRequest> getProvidersAssignedRequests(ServiceProviders serviceProviders) {
        return requestRepository.findServiceProvidersRequests(serviceProviders.getServiceProviders());
    }


    private ServiceRequest get(Long id) {
        return requestRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Service request by id %s not found", id)));
    }
}
