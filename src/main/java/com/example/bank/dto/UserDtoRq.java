package com.example.bank.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDtoRq {
    private String fullName;
    private String phoneNumber;
}
