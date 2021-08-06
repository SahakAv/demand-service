package com.disqo.customerservice.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.Charset;

@Component
public class CustomErrorDecoder implements ErrorDecoder {


    @SneakyThrows
    @Override
    public Exception decode(String methodKey, Response response) {
        ExceptionMessage message = new ObjectMapper()
                .readValue(response.body().asReader(Charset.defaultCharset()), ExceptionMessage.class);
        return new ResponseStatusException(HttpStatus.resolve(response.status()), message.getMessage());
    }
}
