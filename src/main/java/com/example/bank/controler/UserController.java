package com.example.bank.controler;


import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;

import com.example.bank.dto.UserAuthDtoRq;
import com.example.bank.dto.UserDtoRq;
import com.example.bank.dto.UserDtoRs;
import com.example.bank.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUpUser(@RequestBody UserDtoRq userDtoRq) {
        return ResponseEntity.status(CREATED).body(userService.signUp(userDtoRq));
    }

    @PostMapping("/auth")
    public ResponseEntity<String> signInUser(@RequestBody UserAuthDtoRq userAuthDtoRq) {
        return ResponseEntity.status(ACCEPTED).body(userService.signIn(userAuthDtoRq));
    }

    @GetMapping
    public List<UserDtoRs> getAll() {
        return userService.getUsers();
    }
}
