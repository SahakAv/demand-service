package com.disqo.providerservice.repository;

import com.disqo.providerservice.model.domain.ServiceType;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ServiceTypeRepo extends CrudRepository<ServiceType, Long> {


    Optional<ServiceType> findByServiceName(String serviceName);
}
