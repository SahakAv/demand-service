package com.disqo.providerservice.service;

import com.disqo.providerservice.model.domain.ServiceType;

import java.util.List;

public interface ServiceTypeService {


    ServiceType getByName(String serviceTypeName);

    List<ServiceType> getAll();
}
