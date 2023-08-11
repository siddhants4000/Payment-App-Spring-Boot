package com.example.demo.response;

import com.example.demo.entity.Account;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TransactionResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("transaction_id")
    private UUID transactionId;

    @JsonProperty("sender_account_details")
    private Account senderAccount;

    @JsonProperty("receiver_account_details")
    private Account receiverAccount;

    @JsonProperty("transaction_amount")
    private double transactionAmount;

    @JsonProperty("transaction_time")
    private LocalDateTime transactionTime;

    @JsonProperty("transaction_status")
    private String transactionStatus;
}
