package com.example.javaproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.javaproject.model.Account;
import com.example.javaproject.model.Transaction;
import com.example.javaproject.repository.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }

    public List<Transaction> findByFromAccount(Account fromAccount) {
        return transactionRepository.findByFromAccount(fromAccount);
    }

    public List<Transaction> findByToAccount(Account toAccount) {
        return transactionRepository.findByToAccount(toAccount);
    }
}
