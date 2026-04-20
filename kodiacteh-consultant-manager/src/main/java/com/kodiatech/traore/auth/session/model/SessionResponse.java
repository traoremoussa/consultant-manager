package com.kodiatech.traore.auth.session.model;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class SessionResponse {

    private String id;
    private String device;
    private String ipAddress;
    private Instant createdDate;
}
