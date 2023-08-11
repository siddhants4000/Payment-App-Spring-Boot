package com.example.demo.request;

import com.example.demo.entity.Account;
import com.example.demo.enums.UserStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserRequest {

    @NotNull
    @Size(min = 1, max = 10)
    @Valid
    private String name;

    @NotNull
    @Size(min = 10, max = 10)
    private String userContact;

    @NotNull
    private String userAddress;

    @NotNull
    private String userEmail;

    @NotNull
    @Size(min = 3, max = 10)
    private String userName;

    @NotNull
    @Size(min = 3, max = 10)
    private String userPassword;

    @JsonIgnore
    private UserStatus userStatus;

}
