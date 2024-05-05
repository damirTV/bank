package com.example.bank.entity;

import java.math.BigDecimal;
import lombok.Data;
import lombok.NonNull;

@Data
public class Account {
    @NonNull
    private final String account;
    @NonNull
    private final User user;
    @NonNull
    private BigDecimal amount;
}
