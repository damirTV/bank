package com.example.bank.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserAuthDtoRq {
    private String phoneNumber;
    private String pincode;
}
