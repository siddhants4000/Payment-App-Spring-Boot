package com.example.demo.enums;

import lombok.Getter;

@Getter
public enum TransactionStatus {
    SUCCESS("SUCCESS"),
    FAILED("FAILED"),
    IN_PROGRESS("IN_PROGRESS"),
    REFUND_SUCCESSFUL("REFUND_SUCCESSFUL");

    private String transactionStatus;

    TransactionStatus(String transactionStatus) {
        this.transactionStatus=transactionStatus;
    }
}
