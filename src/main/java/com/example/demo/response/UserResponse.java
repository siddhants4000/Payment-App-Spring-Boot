package com.example.demo.response;

import com.example.demo.enums.UserStatus;
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
public class UserResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("user_id")
    private UUID userId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("user_contact")
    private String userContact;

    @JsonProperty("user_address")
    private String userAddress;

    @JsonProperty("user_email")
    private String userEmail;

    @JsonProperty("user_password")
    private String userPassword;

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("user_status")
    private UserStatus userStatus;
}
