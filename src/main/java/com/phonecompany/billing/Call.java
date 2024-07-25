package com.phonecompany.billing;

import java.time.LocalDateTime;

public class Call {
    String phoneNumber;
    LocalDateTime start;
    LocalDateTime end;

    public Call(String phoneNumber, LocalDateTime start, LocalDateTime end) {
        this.phoneNumber = phoneNumber;
        this.start = start;
        this.end = end;
    }
}
