package com.example.demo.controller;

import com.example.demo.model.WrapperResponse;
import com.example.demo.request.AccountAllocationRequest;
import com.example.demo.response.AccountAllocationResponse;
import com.example.demo.service.AccountAllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://127.0.0.1:5500/")
@RestController
@RequestMapping("/allocate")
public class AccountAllocationController {

    @Autowired
    AccountAllocationService accountAllocationService;

    @PostMapping("")
    public WrapperResponse<AccountAllocationResponse> allocate(@RequestBody AccountAllocationRequest accountAllocationRequest) {
        return accountAllocationService.allocate(accountAllocationRequest);
    }

}
