package com.example.bank.repository;

import com.example.bank.entity.User;
import java.util.*;
import org.springframework.stereotype.Component;

@Component
public class UserRepository {
    private final Map<UUID, User> repository = new HashMap<>();

    public void add(User user) {
        repository.put(user.getUuid(), user);
    }

    public List<User> getAll() {
        return new ArrayList<>(repository.values())
                .stream()
                .toList();
    }

    public List<String> getAllPhoneNumber() {
        return new ArrayList<>(repository.values().stream().map(User::getPhoneNumber).toList());
    }

    public User findByPhoneNumber(String phoneNumber) {
        return repository
                .values()
                .stream()
                .filter(user -> user.getPhoneNumber().equals(phoneNumber))
                .findFirst()
                .orElseThrow();
    }
}
