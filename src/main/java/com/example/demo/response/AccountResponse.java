package com.example.demo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AccountResponse {

    private UUID accountId;

    @JsonProperty("account_status")
    private String accountStatus;

    @JsonProperty("account_type")
    private String accountType;

    @JsonProperty("account_balance")
    private double accountBalance;

    @JsonProperty("creation_date_time")
    private LocalDateTime creationDateTime;

    @JsonProperty("discontinuation_date_time")
    private LocalDateTime discontinuationDateTime;

}
