package com.example.demo.enums;

import lombok.Getter;

@Getter
public enum AccountStatus {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE"),
    BLOCKED("BLOCKED");

    String accountStatus;

    AccountStatus(String accountStatus) {
        this.accountStatus=accountStatus;
    }
}
