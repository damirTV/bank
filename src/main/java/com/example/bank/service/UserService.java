package com.example.bank.service;

import com.example.bank.dto.UserAuthDtoRq;
import com.example.bank.dto.UserDtoRq;
import com.example.bank.dto.UserDtoRs;
import com.example.bank.entity.User;
import com.example.bank.repository.UserRepository;
import java.security.SecureRandom;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    @Value("${config.token.prefix}")
    private String PREFIX_TOKEN;
    @Value("${config.token.postfix}")
    private String POSTFIX_TOKEN;
    private final UserRepository userRepository;
    private final AuthService authService;

    public String signUp(UserDtoRq userDtoRq) {
        User user = convertDtoToUser(userDtoRq);
        checkDuplicateUser(user);
        String pincode = createRandomPincode();
        authService.add(user.getUuid(), pincode);
        userRepository.add(user);
        log.info("Добавлен пользователь: {}, телефон: {}, UUID: {}",
                user.getFullName(), user.getPhoneNumber(), user.getUuid());
        return pincode;
    }

    public String signIn(UserAuthDtoRq userAuthDtoRq) {
        User user = userRepository.findByPhoneNumber(userAuthDtoRq.getPhoneNumber());
        UUID uuid = user.getUuid();
        String pincode = userAuthDtoRq.getPincode();
        if (!authService.check(uuid, pincode)) {
            throw new RuntimeException("Некорректный пинкод");
        }
        return createToken(uuid);
    }

    public List<UserDtoRs> getUsers() {
        return userRepository.getAll()
                .stream()
                .map(this::convertUserToDto)
                .toList();
    }

    public User findUserByToken(String token) {
        UUID uuid = ejectUuid(token);
        return userRepository.getAll().stream().filter(user -> user.getUuid().equals(uuid)).findFirst().orElseThrow();
    }

    private User convertDtoToUser(UserDtoRq userDtoRq) {
        return new User(userDtoRq.getFullName(), userDtoRq.getPhoneNumber(), UUID.randomUUID());
    }

    private UserDtoRs convertUserToDto(User user) {
        return new UserDtoRs(user.getFullName(), user.getPhoneNumber(), user.getUuid());
    }

    private void checkDuplicateUser(User user) {
        List<String> phoneNumbers = userRepository.getAllPhoneNumber();
        if (phoneNumbers.contains(user.getPhoneNumber())) {
            log.error("Ошибка создания нового пользователя. Номер {} уже существует", user.getPhoneNumber());
            throw new RuntimeException("Пользователь с таким номером телефона уже существует");
        }
    }

    private String createRandomPincode() {
        SecureRandom random = new SecureRandom();
        int num = random.nextInt(10000);
        return String.format("%04d", num);
    }

    private String createToken(UUID uuid) {
        return PREFIX_TOKEN + uuid + POSTFIX_TOKEN;
    }

    private UUID ejectUuid(String token) {
        int prefixLength = PREFIX_TOKEN.length();
        int postfixLength = POSTFIX_TOKEN.length();
        return UUID.fromString(token.substring(prefixLength, token.length() - postfixLength));
    }
}
