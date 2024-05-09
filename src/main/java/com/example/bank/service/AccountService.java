package com.example.bank.service;

import com.example.bank.dto.AccountDtoRs;
import com.example.bank.dto.UserDtoRs;
import com.example.bank.entity.Account;
import com.example.bank.entity.User;
import com.example.bank.repository.AccountRepository;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public String createNewAccount(User user) {
        Account account = new Account(user, BigDecimal.ZERO);
        accountRepository.add(account);
        return generateNumber(account);
    }

    public void addMoney(Integer number, BigDecimal money) {
        Account account = accountRepository.findByNumber(number);
        account.setAmount(account.getAmount().add(money).setScale(2, RoundingMode.UP));
    }

    public void takeMoney(Integer number, BigDecimal money) {
        Account account = accountRepository.findByNumber(number);
        BigDecimal amount = account.getAmount();
        if (amount.compareTo(money) >= 0) {
            account.setAmount(amount.subtract(money).setScale(2, RoundingMode.UP));
        } else {
            BigDecimal delta = money.subtract(amount);
            account.setAmount(amount.subtract(delta).setScale(2, RoundingMode.UP));
        }
    }

    public List<AccountDtoRs> findAllAccountsByUser(User user) {
        return accountRepository.findAccountByUser(user)
                .stream()
                .map(this::convertAccountToDto)
                .toList();
        //return accountRepository.findAccountByUser(user);
    }

    public BigDecimal giveAmount(Integer number) {
        Account account = accountRepository.findByNumber(number);
        return account.getAmount().setScale(2, RoundingMode.UP);
    }

    public boolean check(User user, Integer number) {
        Account account = accountRepository.findByNumber(number);
        return account.getNumber() == number;
    }

    private String generateNumber(Account account) {
        return String.format("%06d", account.getNumber());
    }

    private AccountDtoRs convertAccountToDto(Account account) {
        return new AccountDtoRs(generateNumber(account), account.getUser(), account.getAmount());
    }
}
