package com.example.demo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AccountAllocationResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("allocation_id")
    private UUID allocationId;

    @JsonProperty("account")
    private AccountResponse accountResponse;

    @JsonProperty("user")
    private UserResponse userResponse;
}
