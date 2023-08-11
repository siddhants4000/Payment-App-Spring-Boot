package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "allocateAccount")
@Entity
public class AccountAllocation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID allocateAccountId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account", referencedColumnName = "accountId")
    private Account account;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_details", referencedColumnName = "userId")
    private User user;

}
