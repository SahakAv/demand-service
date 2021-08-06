package com.disqo.customerservice.model.payload.response;

public enum RequestStatus {
    //Request created by provider not pick up it
    NEW,
    //Provider picked request
    ASSIGNED_TO_PROVIDER,
    //Provider closed request
    CLOSED
}
