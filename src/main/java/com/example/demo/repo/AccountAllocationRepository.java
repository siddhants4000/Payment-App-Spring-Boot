package com.example.demo.repo;

import com.example.demo.entity.Account;
import com.example.demo.entity.AccountAllocation;
import com.example.demo.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Repository
@Transactional
public interface AccountAllocationRepository extends CrudRepository<AccountAllocation, UUID> {
    AccountAllocation findByAccount(Account account);

    AccountAllocation findByUser(User user);
}
