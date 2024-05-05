package com.example.bank.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class AuthRepository {
    private final Map<UUID, String> repository = new HashMap<>();

    public void add(UUID uuid, String pincode) {
        repository.put(uuid, pincode);
    }

    public boolean check(UUID uuid, String pincode) {
        if (!repository.containsKey(uuid)) {
            return false;
        }
        return repository.get(uuid).equals(pincode);
    }
}
