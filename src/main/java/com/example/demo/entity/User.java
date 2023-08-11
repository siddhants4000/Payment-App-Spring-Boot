package com.example.demo.entity;

import com.example.demo.enums.UserStatus;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user_details")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userId;

    @Column(name = "name")
    private String name;

    @Column(name = "user_contact")
    private String userContact;

    @Column(name = "user_address")
    private String userAddress;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_status")
    private UserStatus userStatus;

}
