package com.example.demo.request;

import com.example.demo.enums.AccountType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountRequest {

    @JsonIgnore
    private String accountStatus;

    @NotNull
    private AccountType accountType;

    @NotNull
    private double accountBalance;

    @JsonIgnore
    private LocalDateTime creationDateTime;

    @JsonIgnore
    private LocalDateTime discontinuationDateTime;

}
