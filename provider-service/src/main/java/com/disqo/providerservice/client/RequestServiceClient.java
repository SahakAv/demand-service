package com.disqo.providerservice.client;

import com.disqo.providerservice.model.request.ServiceProviders;
import com.disqo.providerservice.model.request.ServiceTypes;
import com.disqo.providerservice.model.response.ServiceRequestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static com.disqo.providerservice.utils.Constants.RequestMicroServiceConstants.*;


@FeignClient(name = SERVICE_NAME, url = "${request.service.url}")
@Service
public interface RequestServiceClient {


    @PostMapping(value = GET_NEW_REQUESTS, produces = "application/json")
    List<ServiceRequestResponse> getActiveServices(@RequestBody ServiceTypes serviceTypes);

    @GetMapping(value = GET_BY_ID, produces = "application/json")
    ServiceRequestResponse getById(@PathVariable Long requestId);

    @GetMapping(value = ASSIGN_TO_PROVIDER, produces = "application/json")
    ServiceRequestResponse assignToProvider(@PathVariable Long requestId, @PathVariable String providerName);

    @GetMapping(value = CLOSE_REQUEST, produces = "application/json")
    ServiceRequestResponse closeRequest(@PathVariable Long requestId);

    @PostMapping(value = REQUESTS_BY_SERVICE_PROVIDERS, produces = "application/json")
    List<ServiceRequestResponse> getProvidersRequests(@RequestBody ServiceProviders serviceProviders);
}


