package com.example.demo.enums;

import lombok.Getter;

@Getter
public enum AccountType {
    SAVING("SAVING"),
    CURRENT("CURRENT");

    String accountType;

    AccountType(String accountType) {
        this.accountType= accountType;
    }
}
