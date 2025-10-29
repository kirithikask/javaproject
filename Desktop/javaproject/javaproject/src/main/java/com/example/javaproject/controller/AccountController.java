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
import com.example.javaproject.model.User;
import com.example.javaproject.service.AccountService;
import com.example.javaproject.service.UserService;

@Controller
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String listAccounts(Model model) {
        List<Account> accounts = accountService.getAllAccounts();
        model.addAttribute("accounts", accounts);
        return "account-list";
    }

    @GetMapping("/new")
    public String showAccountForm(Model model) {
        model.addAttribute("account", new Account());
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "account-form";
    }

    @PostMapping
    public String saveAccount(@ModelAttribute Account account) {
        accountService.saveAccount(account);
        return "redirect:/accounts";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Account account = accountService.getAccountById(id).orElseThrow(() -> new IllegalArgumentException("Invalid account Id:" + id));
        model.addAttribute("account", account);
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "account-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return "redirect:/accounts";
    }
}
