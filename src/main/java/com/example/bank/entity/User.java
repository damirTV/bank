package com.example.bank.entity;

import java.util.UUID;
import lombok.Data;
import lombok.NonNull;


@Data
public class User {
    @NonNull
    private final String fullName;
    @NonNull
    private final String phoneNumber;
    @NonNull
    private final UUID uuid;
}
