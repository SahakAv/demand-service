package com.disqo.customerservice.client;

import com.disqo.customerservice.model.payload.request.CreateServiceRequest;
import com.disqo.customerservice.model.payload.response.ServiceRequest;
import com.disqo.customerservice.utils.Constants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.disqo.customerservice.utils.Constants.RequestMicroServiceConstants.CREATE_REQUEST_URL;
import static com.disqo.customerservice.utils.Constants.RequestMicroServiceConstants.SERVICE_NAME;


@FeignClient(name = SERVICE_NAME, url = "${request.service.url}")
@Service
public interface RequestServiceClient {


    @RequestMapping(method = RequestMethod.POST, value = CREATE_REQUEST_URL)
    ServiceRequest createService(@RequestBody CreateServiceRequest createServiceRequest);

}
