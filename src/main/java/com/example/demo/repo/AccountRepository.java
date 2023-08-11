package com.example.demo.repo;

import com.example.demo.entity.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Repository
@Transactional
public interface AccountRepository extends CrudRepository<Account, UUID> {

    Account findByAccountId(UUID accountId);
}
