package com.disqo.requestservice.repository;

import com.disqo.requestservice.model.domain.RequestStatus;
import com.disqo.requestservice.model.domain.ServiceRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ServiceRequestRepository extends CrudRepository<ServiceRequest, Long> {

    List<ServiceRequest> findByOwner(String username);

    @Query("SELECT s from ServiceRequest as s where s.serviceType in :serviceTypes AND  s.status = :status")
    List<ServiceRequest> findRequestByTypeAndStatus(List<String> serviceTypes, RequestStatus status);

    @Query("SELECT s from ServiceRequest as s where s.assigned in :serviceProviders")
    List<ServiceRequest> findServiceProvidersRequests(List<String> serviceProviders);

}
