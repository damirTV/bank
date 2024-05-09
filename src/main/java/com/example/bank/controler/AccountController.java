package com.example.bank.controler;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import com.example.bank.dto.AccountDtoRq;
import com.example.bank.dto.AccountDtoRs;
import com.example.bank.entity.Account;
import com.example.bank.entity.User;
import com.example.bank.service.AccountService;
import com.example.bank.service.UserService;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;
    private final UserService userService;

    @GetMapping
    public List<AccountDtoRs> getAllAccountsByUser(@RequestParam String token) {
        User user = userService.findUserByToken(token);
        return ResponseEntity.status(OK).body(accountService.findAllAccountsByUser(user)).getBody();
    }

    @GetMapping("/{account}")
    public BigDecimal getAmount(@PathVariable Integer account) {
        return ResponseEntity.status(OK).body(accountService.giveAmount(account)).getBody();
    }

    @PostMapping
    public ResponseEntity<String> createAccount(@RequestBody AccountDtoRq accountDtoRq) {
        User user = userService.findUserByToken(accountDtoRq.getToken());
        return ResponseEntity.status(CREATED).body(accountService.createNewAccount(user));
    }
}
