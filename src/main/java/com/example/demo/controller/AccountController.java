package com.example.demo.controller;

import com.example.demo.entity.Account;
import com.example.demo.model.WrapperResponse;
import com.example.demo.request.AccountRequest;
import com.example.demo.response.AccountResponse;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://127.0.0.1:5500/")
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping("/add")
    public WrapperResponse<AccountResponse> addAccount(@RequestBody AccountRequest accountRequest) {
        return accountService.addAccount(accountRequest);
    }

    @PostMapping("/delete")
    public WrapperResponse<AccountResponse> deleteAccount(@RequestParam UUID accountId) {
        return accountService.deleteAccount(accountId);
    }

    @PostMapping("/block")
    public WrapperResponse<AccountResponse> blockAccount(@RequestParam UUID accountId) {
        return accountService.blockAccount(accountId);
    }

    @PostMapping("/activate")
    public WrapperResponse<AccountResponse> activateAccount(@RequestParam UUID accountId) {
        return accountService.activateAccount(accountId);
    }

    @GetMapping("/getAll")
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }
}
