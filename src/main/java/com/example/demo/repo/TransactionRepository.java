package com.example.demo.repo;

import com.example.demo.entity.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Repository
@Transactional
public interface TransactionRepository extends CrudRepository<Transaction, UUID> {

    Transaction findByTransactionId(UUID transactionId);
}
