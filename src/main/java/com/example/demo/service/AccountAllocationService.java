package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.entity.AccountAllocation;
import com.example.demo.entity.User;
import com.example.demo.enums.AccountStatus;
import com.example.demo.enums.StatusCode;
import com.example.demo.enums.UserStatus;
import com.example.demo.model.Status;
import com.example.demo.model.WrapperResponse;
import com.example.demo.repo.AccountAllocationRepository;
import com.example.demo.request.AccountAllocationRequest;
import com.example.demo.response.AccountAllocationResponse;
import com.example.demo.response.AccountResponse;
import com.example.demo.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class AccountAllocationService {

    @Autowired
    AccountAllocationRepository accountAllocationRepository;

    @Autowired
    UserService userService;

    @Autowired
    AccountService accountService;

    public WrapperResponse<AccountAllocationResponse> allocate(AccountAllocationRequest accountAllocationRequest) {
        UUID accountId= accountAllocationRequest.getAccountId();
        UUID userId= accountAllocationRequest.getUserId();

        Account account= accountService.accountRepository.findByAccountId(accountId);
        User user= userService.userRepository.findByUserId(userId);

        WrapperResponse accountAllocationResult= new WrapperResponse<>();

        if(Objects.nonNull(accountAllocationRepository.findByAccount(account))) {
            Status resultStatus= Status.builder()
                    .code(StatusCode.SUCCESS.getCode())
                    .message("Account is already allocated")
                    .success(Boolean.TRUE)
                    .build();

            accountAllocationResult= WrapperResponse.<AccountAllocationResponse>builder()
                    .status(resultStatus)
                    .build();

        } else if (AccountStatus.BLOCKED.equals(account.getAccountStatus())) {
            Status resultStatus= Status.builder()
                    .code(StatusCode.SUCCESS.getCode())
                    .message("Account is blocked")
                    .success(Boolean.TRUE)
                    .build();

            accountAllocationResult= WrapperResponse.<AccountAllocationResponse>builder()
                    .status(resultStatus)
                    .build();
        } else if (AccountStatus.INACTIVE.equals(account.getAccountStatus())) {
            Status resultStatus= Status.builder()
                    .code(StatusCode.SUCCESS.getCode())
                    .message("Account is inactive")
                    .success(Boolean.TRUE)
                    .build();

            accountAllocationResult= WrapperResponse.<AccountAllocationResponse>builder()
                    .status(resultStatus)
                    .build();
        } else if (UserStatus.BLOCKED.equals(user.getUserStatus())) {
            Status resultStatus= Status.builder()
                    .code(StatusCode.SUCCESS.getCode())
                    .message("User is blocked")
                    .success(Boolean.TRUE)
                    .build();

            accountAllocationResult= WrapperResponse.<AccountAllocationResponse>builder()
                    .status(resultStatus)
                    .build();
        } else if (UserStatus.INACTIVE.equals(user.getUserStatus())) {
            Status resultStatus= Status.builder()
                    .code(StatusCode.SUCCESS.getCode())
                    .message("User is inactive")
                    .success(Boolean.TRUE)
                    .build();

            accountAllocationResult= WrapperResponse.<AccountAllocationResponse>builder()
                    .status(resultStatus)
                    .build();
        } else if (Objects.nonNull(accountAllocationRepository.findByUser(user))) {
            Status resultStatus= Status.builder()
                    .code(StatusCode.SUCCESS.getCode())
                    .message("User is already allocated")
                    .success(Boolean.TRUE)
                    .build();

            accountAllocationResult= WrapperResponse.<AccountAllocationResponse>builder()
                    .status(resultStatus)
                    .build();

        } else if (Objects.isNull(accountService.accountRepository.findByAccountId(accountId))) {
            Status resultStatus= Status.builder()
                    .code(StatusCode.SUCCESS.getCode())
                    .message("Account does not exists")
                    .success(Boolean.TRUE)
                    .build();

            accountAllocationResult= WrapperResponse.<AccountAllocationResponse>builder()
                    .status(resultStatus)
                    .build();

        } else if(Objects.isNull(userService.userRepository.findByUserId(userId))) {
            Status resultStatus= Status.builder()
                    .code(StatusCode.SUCCESS.getCode())
                    .message("User does not exists")
                    .success(Boolean.TRUE)
                    .build();

            accountAllocationResult= WrapperResponse.<AccountAllocationResponse>builder()
                    .status(resultStatus)
                    .build();

        } else {
            AccountAllocation newAccountAllocation= AccountAllocation.builder()
                    .account(account)
                    .user(user)
                    .build();

            Status resultStatus= Status.builder()
                    .code(StatusCode.SUCCESS.getCode())
                    .message("Account has been allocated successfully.")
                    .success(Boolean.TRUE)
                    .build();

            accountAllocationRepository.save(newAccountAllocation);

            accountAllocationResult= WrapperResponse.<AccountAllocationResponse>builder()
                    .data(AccountAllocationResponse.builder()
                            .allocationId(newAccountAllocation.getAllocateAccountId())
                            .accountResponse(AccountResponse.builder()
                                    .accountId(account.getAccountId())
                                    .accountStatus(account.getAccountStatus())
                                    .accountType(account.getAccountType())
                                    .accountBalance(account.getAccountBalance())
                                    .creationDateTime(account.getCreationDateTime())
                                    .discontinuationDateTime(null)
                                    .build())
                            .userResponse(UserResponse.builder()
                                    .userId(user.getUserId())
                                    .name(user.getName())
                                    .userContact(user.getUserContact())
                                    .userAddress(user.getUserAddress())
                                    .userEmail(user.getUserEmail())
                                    .userPassword(user.getUserPassword())
                                    .userName(user.getUserName())
                                    .userStatus(user.getUserStatus())
                                    .build())
                            .build())
                    .status(resultStatus)
                    .build();
        }
        return accountAllocationResult;
    }
}
