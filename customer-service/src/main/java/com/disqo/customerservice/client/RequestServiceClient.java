package com.disqo.customerservice.client;

import com.disqo.customerservice.model.payload.request.CreateServiceRequest;
import com.disqo.customerservice.model.payload.response.ServiceRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

import static com.disqo.customerservice.utils.Constants.RequestMicroServiceConstants.*;


@FeignClient(name = SERVICE_NAME, url = "${request.service.url}")
@Service
public interface RequestServiceClient {


    @RequestMapping(method = RequestMethod.POST, value = CREATE_REQUEST)
    ServiceRequest createService(@RequestBody CreateServiceRequest createServiceRequest, @RequestHeader String username);


    @RequestMapping(method = RequestMethod.GET, value = GET_CUSTOMER_REQUESTS)
    List<ServiceRequest> getCustomerRequests(@RequestHeader String username);
}
