package com.example.bank.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDtoRs {
    private String fullName;
    private String phoneNumber;
    private UUID uuid;
}
