package com.example.bank.dto;

import com.example.bank.entity.User;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountDtoRs {
    private String account;
    private User user;
    private BigDecimal amount;
}
