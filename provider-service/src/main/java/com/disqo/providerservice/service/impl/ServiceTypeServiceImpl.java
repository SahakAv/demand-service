package com.disqo.providerservice.service.impl;

import com.disqo.providerservice.model.domain.ServiceType;
import com.disqo.providerservice.repository.ServiceTypeRepo;
import com.disqo.providerservice.service.ServiceTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ServiceTypeServiceImpl implements ServiceTypeService {

    private final ServiceTypeRepo serviceTypeRepo;

    public ServiceTypeServiceImpl(ServiceTypeRepo serviceTypeRepo) {
        this.serviceTypeRepo = serviceTypeRepo;
    }

    @Override
    public ServiceType getByName(String serviceTypeName) {
        return serviceTypeRepo.findByServiceName(serviceTypeName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Service type %s not found", serviceTypeName)));
    }


    @Override
    public List<ServiceType> getAll() {
        return StreamSupport.stream(serviceTypeRepo.findAll().spliterator(), true)
                .collect(Collectors.toList());
    }
}
