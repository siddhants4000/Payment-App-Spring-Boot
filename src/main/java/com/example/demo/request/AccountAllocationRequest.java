package com.example.demo.request;

import com.example.demo.entity.User;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountAllocationRequest {

    @NotNull
    private UUID accountId;

    @NotNull
    private UUID userId;
}
