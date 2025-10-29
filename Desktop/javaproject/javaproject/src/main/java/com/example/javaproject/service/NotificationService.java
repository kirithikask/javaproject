package com.example.javaproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.javaproject.model.Notification;
import com.example.javaproject.model.User;
import com.example.javaproject.repository.NotificationRepository;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public Optional<Notification> getNotificationById(Long id) {
        return notificationRepository.findById(id);
    }

    public Notification saveNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }

    public List<Notification> findByUser(User user) {
        return notificationRepository.findByUser(user);
    }

    public List<Notification> findByUserAndIsRead(User user, boolean isRead) {
        return notificationRepository.findByUserAndIsRead(user, isRead);
    }
}
