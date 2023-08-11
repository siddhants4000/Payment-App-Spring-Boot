package com.example.demo.request;

import com.example.demo.entity.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionRequest {

    @JsonIgnore
    private Account senderAccount;

    @JsonIgnore
    private Account receiverAccount;

    @NotNull
    private UUID senderAccountId;

    @NotNull
    private UUID receiverAccountId;

    @NotNull
    private double transactionAmount;

    @JsonIgnore
    private LocalDateTime transactionTime;

    @JsonIgnore
    private String transactionStatus;
}
