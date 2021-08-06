package com.disqo.customerservice.client;

import com.disqo.customerservice.model.payload.response.ServiceProvider;
import com.disqo.customerservice.model.payload.response.ServiceType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.disqo.customerservice.utils.Constants.ServiceProviderMicroServiceConstants.*;

@Service
@FeignClient(name = SERVICE_NAME, url = "${provider.service.url}")
public interface ServiceProviderClient {


    @RequestMapping(method = RequestMethod.GET, value = GET_SERVICE_TYPE_BY_NAME)
    ServiceType getServiceTypeByName(@PathVariable("name") String name);


    @RequestMapping(method = RequestMethod.GET, value = GET_SERVICE_PROVIDER_BY_NAME)
    ServiceProvider getServiceProviderByName(@PathVariable("name") String name);
}
