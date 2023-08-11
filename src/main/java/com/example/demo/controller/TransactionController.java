package com.example.demo.controller;

import com.example.demo.entity.Transaction;
import com.example.demo.model.WrapperResponse;
import com.example.demo.request.TransactionRequest;
import com.example.demo.response.AccountResponse;
import com.example.demo.response.TransactionResponse;
import com.example.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/send")
    public WrapperResponse<TransactionResponse> sendMoney(@RequestBody TransactionRequest transactionRequest) {
        return transactionService.sendMoney(transactionRequest);
    }

    @PostMapping("/addMoney")
    public WrapperResponse<AccountResponse> addMoney(
            @RequestParam UUID accountId,
            @RequestParam double amount) {
        return transactionService.addMoney(accountId, amount);
    }

    @PostMapping("/refund")
    public WrapperResponse<TransactionResponse> refund(@RequestParam UUID transactionId) {
        return transactionService.refund(transactionId);
    }

    @GetMapping("/getAll")
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }
}
