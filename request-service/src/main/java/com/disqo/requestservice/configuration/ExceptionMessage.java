package com.disqo.requestservice.configuration;

import lombok.Data;

@Data
public class ExceptionMessage {
    private String timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
}
