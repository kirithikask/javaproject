package com.example.javaproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.javaproject.model.Account;
import com.example.javaproject.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByFromAccount(Account fromAccount);
    List<Transaction> findByToAccount(Account toAccount);
}
