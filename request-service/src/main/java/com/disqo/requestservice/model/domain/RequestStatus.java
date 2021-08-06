package com.disqo.requestservice.model.domain;

public enum RequestStatus {
    //Request created by provider not pick up it
    NEW,
    //Provider picked request
    ASSIGNED_TO_PROVIDER;
}
