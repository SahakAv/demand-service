package com.disqo.providerservice.repository;

import com.disqo.providerservice.model.domain.ServiceProvider;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceProviderRepository extends CrudRepository<ServiceProvider, Long> {


    @Query("SELECT serviceprovider from ServiceProvider as serviceprovider where " +
            "serviceprovider.serviceType.serviceName = :serviceType")
    List<ServiceProvider> findAllByServiceType(String serviceType);


    Optional<ServiceProvider> findByName(String name);

    List<ServiceProvider> findByOwner(String username);
}
