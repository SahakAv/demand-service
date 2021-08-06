package com.disqo.providerservice.service.impl;

import com.disqo.providerservice.model.domain.ServiceProvider;
import com.disqo.providerservice.model.domain.ServiceType;
import com.disqo.providerservice.model.request.CreateServiceProviderRequest;
import com.disqo.providerservice.model.response.ServiceProviderResponse;
import com.disqo.providerservice.repository.ServiceProviderRepository;
import com.disqo.providerservice.repository.ServiceTypeRepo;
import com.disqo.providerservice.service.ServiceProviderService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ServiceProviderServiceImpl implements ServiceProviderService {

    private final ServiceProviderRepository serviceProviderRepo;
    private final ServiceTypeRepo serviceTypeRepo;

    public ServiceProviderServiceImpl(ServiceProviderRepository repo, ServiceTypeRepo serviceTypeRepo) {
        this.serviceProviderRepo = repo;
        this.serviceTypeRepo = serviceTypeRepo;
    }


    @Override
    public ServiceProvider create(CreateServiceProviderRequest request, String owner) {
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
}
