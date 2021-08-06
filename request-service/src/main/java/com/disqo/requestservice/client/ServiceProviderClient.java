package com.disqo.requestservice.client;

import com.disqo.requestservice.model.response.ServiceType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.disqo.requestservice.utils.Constants.ServiceProviderMicroServiceConstants.GET_SERVICE_TYPE_BY_NAME;
import static com.disqo.requestservice.utils.Constants.ServiceProviderMicroServiceConstants.SERVICE_NAME;

@FeignClient(name = SERVICE_NAME, url = "${service.provider.url}")
public interface ServiceProviderClient {


    @RequestMapping(method = RequestMethod.GET, value = GET_SERVICE_TYPE_BY_NAME)
    ServiceType getServiceTypeByName(@PathVariable("name") String name);
}
