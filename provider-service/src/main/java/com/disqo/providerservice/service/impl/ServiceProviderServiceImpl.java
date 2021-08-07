package com.disqo.providerservice.service.impl;

import com.disqo.providerservice.client.RequestServiceClient;
import com.disqo.providerservice.model.domain.ServiceProvider;
import com.disqo.providerservice.model.domain.ServiceType;
import com.disqo.providerservice.model.request.CreateServiceProviderRequest;
import com.disqo.providerservice.model.request.ServiceProviders;
import com.disqo.providerservice.model.request.ServiceTypes;
import com.disqo.providerservice.model.response.ServiceProviderResponse;
import com.disqo.providerservice.model.response.ServiceRequestResponse;
import com.disqo.providerservice.repository.ServiceProviderRepository;
import com.disqo.providerservice.repository.ServiceTypeRepo;
import com.disqo.providerservice.service.ServiceProviderService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ServiceProviderServiceImpl implements ServiceProviderService {

    private final ServiceProviderRepository serviceProviderRepo;
    private final ServiceTypeRepo serviceTypeRepo;
    private final RequestServiceClient requestServiceClient;

    public ServiceProviderServiceImpl(ServiceProviderRepository repo, ServiceTypeRepo serviceTypeRepo, RequestServiceClient requestServiceClient) {
        this.serviceProviderRepo = repo;
        this.serviceTypeRepo = serviceTypeRepo;
        this.requestServiceClient = requestServiceClient;
    }


    @Override
    public ServiceProvider create(CreateServiceProviderRequest request, String owner) {
        if (serviceProviderRepo.findByName(request.getServiceProviderName()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("Service provider with name %s already exist", request.getServiceProviderName()));
        }
        //Get service type if exist, else create new one
        final ServiceType serviceType = serviceTypeRepo.findByServiceName(request.getServiceTypeName())
                .orElseGet(() -> {
                    ServiceType newType = new ServiceType();
                    newType.setServiceName(request.getServiceTypeName());
                    return serviceTypeRepo.save(newType);
                });
        final ServiceProvider serviceProvider = new ServiceProvider(request, serviceType, owner);
        return serviceProviderRepo.save(serviceProvider);
    }

    @Override
    public List<ServiceProviderResponse> getAllByServiceType(String serviceType) {
        return serviceProviderRepo.findAllByServiceType(serviceType).stream().map(ServiceProviderResponse::new
        ).collect(Collectors.toList());
    }


    @Override
    public ServiceProviderResponse getByName(String name) {
        return serviceProviderRepo.findByName(name)
                .map(ServiceProviderResponse::new).orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("Service provider by name %s not found", name)));
    }

    @Override
    public List<ServiceProviderResponse> getAll() {
        return StreamSupport.stream(serviceProviderRepo.findAll().spliterator(), true).map(
                ServiceProviderResponse::new).collect(Collectors.toList());
    }

    @Override
    public List<ServiceRequestResponse> getAcceptedRequests(String username) {
        List<String> providers = serviceProviderRepo.findByOwner(username)
                .stream().map(ServiceProvider::getName).collect(Collectors.toList());
        return requestServiceClient.getProvidersRequests(new ServiceProviders(providers));
    }

    @Override
    public List<ServiceRequestResponse> getActives(String username) {
        //Get service provider all registered service types
        List<String> serviceTypes = getProviderSupportedServiceTypes(username);

        if (!serviceTypes.isEmpty()) {
            //Get new requests by a service provider service types
            return requestServiceClient.getActiveServices(new ServiceTypes(serviceTypes));
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public ServiceRequestResponse acceptRequest(String username, Long requestId, String providerName) {
        ServiceRequestResponse serviceRequest = requestServiceClient.getById(requestId);
        validate(providerName, serviceRequest, username);
        serviceProviderRepo.findByOwner(username);
        return requestServiceClient.assignToProvider(requestId, providerName);
    }

    @Override
    public ServiceRequestResponse closeRequest(String username, Long requestId) {
        ServiceRequestResponse serviceRequest = requestServiceClient.getById(requestId);
        List<String> userServiceProvidersNames = serviceProviderRepo.findByOwner(username)
                .stream().map(ServiceProvider::getName).collect(Collectors.toList());
        if (!userServiceProvidersNames.contains(serviceRequest.getAssigned())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Only service provider can close request");
        }
        return requestServiceClient.closeRequest(requestId);
    }

    private void validate(String providerName, ServiceRequestResponse serviceRequest, String username) {
        Optional<ServiceProvider> byName = serviceProviderRepo.findByName(providerName);
        validateIsOwner(username, byName);
        validateServiceTypeSupportedByProvider(byName.get().getServiceType().getServiceName(), serviceRequest);
    }


    private void validateServiceTypeSupportedByProvider(String providerSupportedServiceType,
                                                        ServiceRequestResponse requestResponse) {
        if (!providerSupportedServiceType.equals(requestResponse.getServiceType())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Service request type is not supported by provider");
        }
    }

    private void validateIsOwner(String username, Optional<ServiceProvider> byName) {
        if (byName.isPresent()) {
            if (!byName.get().getOwner().equals(username)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Only service provider owner allowed to accept request");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Service provider not found");
        }
    }

    private List<String> getProviderSupportedServiceTypes(String username) {
        return serviceProviderRepo.findByOwner(username)
                .stream().map(ServiceProvider::getServiceType).distinct()
                .map(ServiceType::getServiceName).collect(Collectors.toList());
    }
}
