package com.disqo.customerservice.service.impl;

import com.disqo.customerservice.client.RequestServiceClient;
import com.disqo.customerservice.client.ServiceProviderClient;
import com.disqo.customerservice.model.payload.request.CreateServiceRequest;
import com.disqo.customerservice.model.payload.response.ServiceProvider;
import com.disqo.customerservice.model.payload.response.ServiceRequest;
import com.disqo.customerservice.model.payload.response.ServiceType;
import com.disqo.customerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final RequestServiceClient serviceClient;

    private final ServiceProviderClient serviceProviderClient;

    public CustomerServiceImpl(RequestServiceClient serviceClient, ServiceProviderClient serviceProviderClient) {
        this.serviceClient = serviceClient;
        this.serviceProviderClient = serviceProviderClient;
    }


    @Override
    public ServiceRequest createServiceRequest(CreateServiceRequest serviceRequest) {
        ServiceType serviceType = serviceProviderClient.getServiceTypeByName(serviceRequest.getServiceType());;
         validateServiceProvider(serviceRequest, serviceType);
        return serviceClient.createService(serviceRequest);
    }


    private void validateServiceProvider(CreateServiceRequest serviceRequest, ServiceType serviceType) {
        if (serviceRequest.getServiceProvider().isPresent()) {
            String serviceProviderName = serviceRequest.getServiceProvider().get();
            ServiceProvider serviceProvider = serviceProviderClient.getServiceProviderByName(serviceProviderName);
            if (!serviceProvider.getServiceType().getServiceName().equals(serviceRequest.getServiceType())) {
                //Requested service type amd service provider service type should be same
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        String.format("Service provider %s service type is %s, but requested service %s",
                                serviceProviderName, serviceProvider.getServiceType().getServiceName(), serviceType.getServiceName()));
            }
        }
    }
}
