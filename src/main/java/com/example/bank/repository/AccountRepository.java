package com.example.bank.repository;

import com.example.bank.entity.Account;
import com.example.bank.entity.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class AccountRepository {
    private final Map<String, Account> repository = new HashMap<>();

    public void add(Account account) {
        repository.put(account.getAccount(), account);
    }

    public Account findByNumber(String number) {
        if (repository.containsKey(number)) {
            return repository.get(number);
        }
        throw new RuntimeException("Счёт с таким номером не найден");
    }

    public List<Account> findAccountByUser(User user) {
        return repository.values().stream()
                .filter(account -> account.getUser().equals(user))
                .toList();
    }
}
