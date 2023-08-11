package com.example.demo.enums;

import lombok.Getter;

@Getter
public enum UserStatus {

    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE"),
    BLOCKED("BLOCKED");

    String userStatus;

    UserStatus(String userStatus) {
        this.userStatus=userStatus;
    }
}
