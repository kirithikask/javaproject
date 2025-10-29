package com.example.javaproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.javaproject.model.Account;
import com.example.javaproject.model.Transaction;
import com.example.javaproject.service.AccountService;
import com.example.javaproject.service.TransactionService;

@Controller
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AccountService accountService;

    @GetMapping
    public String listTransactions(Model model) {
        List<Transaction> transactions = transactionService.getAllTransactions();
        model.addAttribute("transactions", transactions);
        return "transaction-list";
    }

    @GetMapping("/new")
    public String showTransactionForm(Model model) {
        model.addAttribute("transaction", new Transaction());
        List<Account> accounts = accountService.getAllAccounts();
        model.addAttribute("accounts", accounts);
        return "transaction-form";
    }

    @PostMapping
    public String saveTransaction(@ModelAttribute Transaction transaction) {
        transactionService.saveTransaction(transaction);
        return "redirect:/transactions";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Transaction transaction = transactionService.getTransactionById(id).orElseThrow(() -> new IllegalArgumentException("Invalid transaction Id:" + id));
        model.addAttribute("transaction", transaction);
        List<Account> accounts = accountService.getAllAccounts();
        model.addAttribute("accounts", accounts);
        return "transaction-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return "redirect:/transactions";
    }
}
