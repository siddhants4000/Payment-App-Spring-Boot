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
@Table(name = "transaction")
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID transactionId;

    @OneToOne
    @JoinColumn(name = "sender_account_id", referencedColumnName = "accountId")
    private Account senderAccount;

    @OneToOne
    @JoinColumn(name = "receiver_account_id", referencedColumnName = "accountId")
    private Account receiverAccount;

    @Column(name = "transaction_amount")
    private double transactionAmount;

    @Column(name = "transaction_time")
    private LocalDateTime transactionTime;

    @Column(name = "transaction_status")
    private String transactionStatus;
}
