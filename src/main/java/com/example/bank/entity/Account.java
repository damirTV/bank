package com.example.bank.entity;

import java.math.BigDecimal;
import lombok.*;

@Getter
@Setter
public class Account {
    private final int number;
    private static int counter;
    private final User user;
    private BigDecimal amount;

    public Account(User user, @NonNull BigDecimal amount) {
        counter++;
        number = counter;
        this.user = user;
        this.amount = amount;
    }
}
