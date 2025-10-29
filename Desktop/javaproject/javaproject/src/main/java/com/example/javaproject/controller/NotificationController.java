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

import com.example.javaproject.model.Notification;
import com.example.javaproject.model.User;
import com.example.javaproject.service.NotificationService;
import com.example.javaproject.service.UserService;

@Controller
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String listNotifications(Model model) {
        List<Notification> notifications = notificationService.getAllNotifications();
        model.addAttribute("notifications", notifications);
        return "notification-list";
    }

    @GetMapping("/new")
    public String showNotificationForm(Model model) {
        model.addAttribute("notification", new Notification());
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "notification-form";
    }

    @PostMapping
    public String saveNotification(@ModelAttribute Notification notification) {
        notificationService.saveNotification(notification);
        return "redirect:/notifications";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Notification notification = notificationService.getNotificationById(id).orElseThrow(() -> new IllegalArgumentException("Invalid notification Id:" + id));
        model.addAttribute("notification", notification);
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "notification-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
        return "redirect:/notifications";
    }
}
