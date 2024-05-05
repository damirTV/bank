package com.example.bank.repository;

import com.example.bank.entity.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class UserRepository {
    private final Map<String, User> repository = new HashMap<>();

    public void add(User user) {
        repository.put(user.getPhoneNumber(), user);
    }

    public List<User> getAll() {
        return new ArrayList<>(repository.values())
                .stream()
                .toList();
    }

    public List<String> getAllPhoneNumber() {
        return new ArrayList<>(repository.keySet());
    }

    public User findByPhoneNumber(String phoneNumber) {
        if (repository.containsKey(phoneNumber)) {
            return repository.get(phoneNumber);
        }
        throw new RuntimeException("Пользователь с таким номером не найден");
    }
}
