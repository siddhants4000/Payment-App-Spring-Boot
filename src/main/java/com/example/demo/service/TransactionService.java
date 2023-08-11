package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.entity.Transaction;
import com.example.demo.enums.AccountStatus;
import com.example.demo.enums.StatusCode;
import com.example.demo.enums.TransactionStatus;
import com.example.demo.model.Status;
import com.example.demo.model.WrapperResponse;
import com.example.demo.repo.TransactionRepository;
import com.example.demo.request.TransactionRequest;
import com.example.demo.response.AccountResponse;
import com.example.demo.response.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountService accountService;

    public WrapperResponse<TransactionResponse> sendMoney(TransactionRequest transactionRequest) {
        Account senderAccount= accountService.accountRepository.findByAccountId(transactionRequest.getSenderAccountId());
        Account receiverAccount= accountService.accountRepository.findByAccountId(transactionRequest.getReceiverAccountId());
        if(Objects.isNull(accountService.accountRepository.findByAccountId(transactionRequest.getSenderAccountId()))) {
            Status resultStatus= Status.builder()
                    .code(StatusCode.BAD_REQUEST.getCode())
                    .message("Sender Account does not exists")
                    .success(Boolean.TRUE)
                    .build();
            return WrapperResponse.<TransactionResponse>builder()
                    .status(resultStatus)
                    .build();
        } else if (Objects.isNull(accountService.accountRepository.findByAccountId(transactionRequest.getReceiverAccountId()))) {
            Status resultStatus= Status.builder()
                    .code(StatusCode.BAD_REQUEST.getCode())
                    .message("Receiver Account does not exists")
                    .success(Boolean.TRUE)
                    .build();
            return WrapperResponse.<TransactionResponse>builder()
                    .status(resultStatus)
                    .build();
        } else if (Objects.nonNull(senderAccount) && Objects.nonNull(receiverAccount) && transactionRequest.getTransactionAmount()>accountService.accountRepository.findByAccountId(transactionRequest.getSenderAccountId()).getAccountBalance()) {
            Status resultStatus = Status.builder()
                    .code(StatusCode.BAD_REQUEST.getCode())
                    .message("Insufficient Amount in Sender Account")
                    .success(Boolean.TRUE)
                    .build();
            return WrapperResponse.<TransactionResponse>builder()
                    .status(resultStatus)
                    .build();
        } else {
            Transaction newTransaction= Transaction.builder()
                    .senderAccount(senderAccount)
                    .receiverAccount(receiverAccount)
                    .transactionAmount(transactionRequest.getTransactionAmount())
                    .transactionTime(LocalDateTime.now())
                    .transactionStatus(TransactionStatus.IN_PROGRESS.getTransactionStatus())
                    .build();

            Status resultStatus = Status.builder()
                    .code(StatusCode.SUCCESS.getCode())
                    .message("Success")
                    .success(Boolean.TRUE)
                    .build();

            transactionRepository.save(newTransaction);

            senderAccount.setAccountBalance(accountService.accountRepository.findByAccountId(transactionRequest.getSenderAccountId()).getAccountBalance()-transactionRequest.getTransactionAmount());
            receiverAccount.setAccountBalance(accountService.accountRepository.findByAccountId(transactionRequest.getReceiverAccountId()).getAccountBalance()+transactionRequest.getTransactionAmount());

            accountService.accountRepository.save(senderAccount);
            accountService.accountRepository.save(receiverAccount);

            return WrapperResponse.<TransactionResponse>builder()
                    .data(TransactionResponse.builder()
                            .transactionId(newTransaction.getTransactionId())
                            .senderAccount(newTransaction.getSenderAccount())
                            .receiverAccount(newTransaction.getReceiverAccount())
                            .transactionAmount(newTransaction.getTransactionAmount())
                            .transactionTime(newTransaction.getTransactionTime())
                            .transactionStatus(newTransaction.getTransactionStatus())
                            .build())
                    .status(resultStatus)
                    .build();
        }
    }

    public WrapperResponse<AccountResponse> addMoney(UUID accountId, double amount) {
        Account receiverAccount= accountService.accountRepository.findByAccountId(accountId);
        if(Objects.isNull(receiverAccount)){
            Status resultStatus= Status.builder()
                    .code(StatusCode.BAD_REQUEST.getCode())
                    .message("Receiver Account does not exists")
                    .success(Boolean.TRUE)
                    .build();
            return WrapperResponse.<AccountResponse>builder()
                    .status(resultStatus)
                    .build();
        } else if (AccountStatus.INACTIVE.equals(receiverAccount.getAccountStatus()) || AccountStatus.BLOCKED.equals(receiverAccount.getAccountStatus())) {
            Status resultStatus= Status.builder()
                    .code(StatusCode.BAD_REQUEST.getCode())
                    .message("Account is not active")
                    .success(Boolean.TRUE)
                    .build();
            return WrapperResponse.<AccountResponse>builder()
                    .status(resultStatus)
                    .build();
        } else {
            receiverAccount.setAccountBalance(receiverAccount.getAccountBalance()+amount);
            accountService.accountRepository.save(receiverAccount);

            Status resultStatus= Status.builder()
                    .code(StatusCode.SUCCESS.getCode())
                    .message("Amount added to account")
                    .success(Boolean.TRUE)
                    .build();
            return WrapperResponse.<AccountResponse>builder()
                    .data(AccountResponse.builder()
                            .accountId(receiverAccount.getAccountId())
                            .accountStatus(receiverAccount.getAccountStatus())
                            .accountType(receiverAccount.getAccountType())
                            .accountBalance(receiverAccount.getAccountBalance())
                            .creationDateTime(receiverAccount.getCreationDateTime())
                            .discontinuationDateTime(receiverAccount.getDiscontinuationDateTime())
                            .build())
                    .status(resultStatus)
                    .build();
        }
    }

    public WrapperResponse<TransactionResponse> refund(UUID transactionId) {
        Transaction transaction= transactionRepository.findByTransactionId(transactionId);
        Account receiverAccount= transaction.getSenderAccount();
        Account senderAccount= transaction.getReceiverAccount();
        if (Objects.isNull(transaction)) {
            Status resultStatus= Status.builder()
                    .code(StatusCode.BAD_REQUEST.getCode())
                    .message("No Transaction Found")
                    .success(Boolean.TRUE)
                    .build();
            return WrapperResponse.<TransactionResponse>builder()
                    .status(resultStatus)
                    .build();
        } else if (transaction.getTransactionAmount()>accountService.accountRepository.findByAccountId(transaction.getReceiverAccount().getAccountId()).getAccountBalance()) {
            Status resultStatus = Status.builder()
                    .code(StatusCode.BAD_REQUEST.getCode())
                    .message("Insufficient Amount in Sender Account")
                    .success(Boolean.TRUE)
                    .build();
            return WrapperResponse.<TransactionResponse>builder()
                    .status(resultStatus)
                    .build();
        } else if (AccountStatus.BLOCKED.getAccountStatus().equals(transaction.getSenderAccount().getAccountStatus()) || AccountStatus.INACTIVE.getAccountStatus().equals(transaction.getSenderAccount().getAccountStatus())) {
            Status resultStatus = Status.builder()
                    .code(StatusCode.BAD_REQUEST.getCode())
                    .message("Receiver Account is not active")
                    .success(Boolean.TRUE)
                    .build();
            return WrapperResponse.<TransactionResponse>builder()
                    .status(resultStatus)
                    .build();
        } else if (AccountStatus.BLOCKED.getAccountStatus().equals(transaction.getReceiverAccount().getAccountStatus()) || AccountStatus.INACTIVE.getAccountStatus().equals(transaction.getReceiverAccount().getAccountStatus())) {
            Status resultStatus = Status.builder()
                    .code(StatusCode.BAD_REQUEST.getCode())
                    .message("Sender Account is not active")
                    .success(Boolean.TRUE)
                    .build();
            return WrapperResponse.<TransactionResponse>builder()
                    .status(resultStatus)
                    .build();
        } else if (TransactionStatus.REFUND_SUCCESSFUL.getTransactionStatus().equals(transaction.getTransactionStatus())) {
            Status resultStatus = Status.builder()
                    .code(StatusCode.BAD_REQUEST.getCode())
                    .message("Refund already done")
                    .success(Boolean.TRUE)
                    .build();
            return WrapperResponse.<TransactionResponse>builder()
                    .status(resultStatus)
                    .build();
        } else {
            transaction.setTransactionStatus(TransactionStatus.REFUND_SUCCESSFUL.getTransactionStatus());

            Status resultStatus = Status.builder()
                    .code(StatusCode.SUCCESS.getCode())
                    .message("Success")
                    .success(Boolean.TRUE)
                    .build();

            transactionRepository.save(transaction);

            senderAccount.setAccountBalance(transaction.getReceiverAccount().getAccountBalance()-transaction.getTransactionAmount());
            receiverAccount.setAccountBalance(transaction.getSenderAccount().getAccountBalance()+transaction.getTransactionAmount());

            accountService.accountRepository.save(senderAccount);
            accountService.accountRepository.save(receiverAccount);

            return WrapperResponse.<TransactionResponse>builder()
                    .data(TransactionResponse.builder()
                            .transactionId(transaction.getTransactionId())
                            .senderAccount(transaction.getSenderAccount())
                            .receiverAccount(transaction.getReceiverAccount())
                            .transactionAmount(transaction.getTransactionAmount())
                            .transactionTime(transaction.getTransactionTime())
                            .transactionStatus(transaction.getTransactionStatus())
                            .build())
                    .status(resultStatus)
                    .build();
        }
    }

    public List<Transaction> getAllTransactions() {
        return (List<Transaction>) transactionRepository.findAll();
    }
}
