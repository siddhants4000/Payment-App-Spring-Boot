package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.enums.AccountStatus;
import com.example.demo.enums.StatusCode;
import com.example.demo.model.Status;
import com.example.demo.model.WrapperResponse;
import com.example.demo.repo.AccountRepository;
import com.example.demo.request.AccountRequest;
import com.example.demo.response.AccountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public WrapperResponse<AccountResponse> addAccount(AccountRequest accountRequest) {
            Account newAccount= Account.builder()
                    .accountStatus(AccountStatus.ACTIVE.getAccountStatus())
                    .accountType(accountRequest.getAccountType())
                    .accountBalance(accountRequest.getAccountBalance())
                    .creationDateTime(LocalDateTime.now())
                    .build();

            Status resultStatus= Status.builder()
                    .code(StatusCode.SUCCESS.getCode())
                    .message("Account has been added successfully.")
                    .success(Boolean.TRUE)
                    .build();

            accountRepository.save(newAccount);

            return WrapperResponse.<AccountResponse>builder()
                    .data(AccountResponse.builder()
                            .accountId(newAccount.getAccountId())
                            .accountStatus(newAccount.getAccountStatus())
                            .accountType(newAccount.getAccountType())
                            .accountBalance(newAccount.getAccountBalance())
                            .creationDateTime(newAccount.getCreationDateTime())
                            .discontinuationDateTime(null)
                            .build())
                    .status(resultStatus)
                    .build();
    }

    public WrapperResponse<AccountResponse> deleteAccount(UUID accountId) {
        Account account=accountRepository.findByAccountId(accountId);
        if(Objects.isNull(account)) {
            Status resultStatus= Status.builder()
                    .code(StatusCode.BAD_REQUEST.getCode())
                    .message("Account does not exists")
                    .success(Boolean.TRUE)
                    .build();
            return WrapperResponse.<AccountResponse>builder()
                    .status(resultStatus)
                    .build();
        } else if (AccountStatus.INACTIVE.equals(account.getAccountStatus())) {
            Status resultStatus = Status.builder()
                    .code(StatusCode.BAD_REQUEST.getCode())
                    .message("Account is already deleted")
                    .success(Boolean.TRUE)
                    .build();
            return WrapperResponse.<AccountResponse>builder()
                    .status(resultStatus)
                    .build();
        } else {
            account.setAccountStatus(AccountStatus.INACTIVE.getAccountStatus());

            accountRepository.save(account);

            Status resultStatus= Status.builder()
                    .code(StatusCode.SUCCESS.getCode())
                    .message("Account has been deleted successfully.")
                    .success(Boolean.TRUE)
                    .build();
            return WrapperResponse.<AccountResponse>builder()
                    .data(AccountResponse.builder()
                            .accountId(account.getAccountId())
                            .accountStatus(account.getAccountStatus())
                            .accountType(account.getAccountType())
                            .accountBalance(account.getAccountBalance())
                            .creationDateTime(account.getCreationDateTime())
                            .discontinuationDateTime(account.getDiscontinuationDateTime())
                            .build())
                    .status(resultStatus)
                    .build();
        }
    }

    public WrapperResponse<AccountResponse> blockAccount(UUID accountId) {
        Account account=accountRepository.findByAccountId(accountId);
        if(Objects.isNull(account)) {
            Status resultStatus= Status.builder()
                    .code(StatusCode.BAD_REQUEST.getCode())
                    .message("Account does not exists")
                    .success(Boolean.TRUE)
                    .build();
            return WrapperResponse.<AccountResponse>builder()
                    .status(resultStatus)
                    .build();
        } else if (AccountStatus.BLOCKED.equals(account.getAccountStatus())) {
            Status resultStatus= Status.builder()
                    .code(StatusCode.BAD_REQUEST.getCode())
                    .message("Account is already blocked")
                    .success(Boolean.TRUE)
                    .build();
            return WrapperResponse.<AccountResponse>builder()
                    .status(resultStatus)
                    .build();
        } else {
            account.setAccountStatus(AccountStatus.BLOCKED.getAccountStatus());

            accountRepository.save(account);

            Status resultStatus= Status.builder()
                    .code(StatusCode.SUCCESS.getCode())
                    .message("Account has been blocked successfully.")
                    .success(Boolean.TRUE)
                    .build();
            return WrapperResponse.<AccountResponse>builder()
                    .data(AccountResponse.builder()
                            .accountId(account.getAccountId())
                            .accountStatus(account.getAccountStatus())
                            .accountType(account.getAccountType())
                            .accountBalance(account.getAccountBalance())
                            .creationDateTime(account.getCreationDateTime())
                            .discontinuationDateTime(account.getDiscontinuationDateTime())
                            .build())
                    .status(resultStatus)
                    .build();
        }
    }

    public WrapperResponse<AccountResponse> activateAccount(UUID accountId) {
        Account account=accountRepository.findByAccountId(accountId);
        if(Objects.isNull(account)) {
            Status resultStatus= Status.builder()
                    .code(StatusCode.BAD_REQUEST.getCode())
                    .message("Account does not exists")
                    .success(Boolean.TRUE)
                    .build();
            return WrapperResponse.<AccountResponse>builder()
                    .status(resultStatus)
                    .build();
        } else if (AccountStatus.ACTIVE.equals(account.getAccountStatus())) {
            Status resultStatus = Status.builder()
                    .code(StatusCode.BAD_REQUEST.getCode())
                    .message("Account is already active")
                    .success(Boolean.TRUE)
                    .build();
            return WrapperResponse.<AccountResponse>builder()
                    .status(resultStatus)
                    .build();
        } else {
            account.setAccountStatus(AccountStatus.ACTIVE.getAccountStatus());

            accountRepository.save(account);

            Status resultStatus= Status.builder()
                    .code(StatusCode.SUCCESS.getCode())
                    .message("Account has been activated successfully.")
                    .success(Boolean.TRUE)
                    .build();
            return WrapperResponse.<AccountResponse>builder()
                    .data(AccountResponse.builder()
                            .accountId(account.getAccountId())
                            .accountStatus(account.getAccountStatus())
                            .accountType(account.getAccountType())
                            .accountBalance(account.getAccountBalance())
                            .creationDateTime(account.getCreationDateTime())
                            .discontinuationDateTime(account.getDiscontinuationDateTime())
                            .build())
                    .status(resultStatus)
                    .build();
        }
    }

    public List<Account> getAllAccounts() {
        return (List<Account>) accountRepository.findAll();
    }
}
