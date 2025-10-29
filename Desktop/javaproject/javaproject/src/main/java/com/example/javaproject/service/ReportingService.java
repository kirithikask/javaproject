package com.example.javaproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.javaproject.model.Transaction;
import com.example.javaproject.model.User;
import com.example.javaproject.repository.TransactionRepository;

@Service
public class ReportingService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getTransactionHistory(User user) {
        // Assuming transactions are linked via accounts, but for simplicity, return all for now
        // In real implementation, filter by user's accounts
        return transactionRepository.findAll();
    }

    // Add more reporting methods as needed
}
