package com.example.bank.service;

import com.example.bank.repository.AuthRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;

    public void add(UUID uuid, String pincode) {
        authRepository.add(uuid, pincode);
        log.info("Добавлен UUID: {}, pincode: {}", uuid, pincode);
    }

    public boolean check(UUID uuid, String pincode) {
        return authRepository.check(uuid, pincode);
    }
}
