package com.disqo.requestservice.repository;

import com.disqo.requestservice.model.domain.ServiceRequest;
import org.springframework.data.repository.CrudRepository;

public interface ServiceRequestRepository extends CrudRepository<ServiceRequest, Long> {
}
