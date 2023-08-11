package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "account")
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID accountId;

    @Column(name = "account_status")
    private String accountStatus;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "account_balance")
    private double accountBalance;

    @Column(name = "creation_date_time")
    private LocalDateTime creationDateTime;

    @Column(name = "discontinuation_date_time")
    private LocalDateTime discontinuationDateTime;

}
