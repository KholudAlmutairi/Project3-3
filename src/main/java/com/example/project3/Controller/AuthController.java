package com.example.project3.Controller;

import com.example.project3.Api.ApiResponse;
import com.example.project3.Model.User;
import com.example.project3.Service.AccountService;
import com.example.project3.Service.AuthService;
import com.example.project3.Service.CustomerService;
import com.example.project3.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final EmployeeService employeeService;
    private final CustomerService customerService;
    private final AccountService accountService;


    @GetMapping("/get-all")
    public ResponseEntity getAllAccounts() {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getAllAccounts());
    }


    @GetMapping("/get-allUser")
    public ResponseEntity getAllUsers(){//admin

        return ResponseEntity.status(200).body(authService.getAllUsers());
    }


    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user){
        authService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("User Added"));
    }

    @PutMapping("/update")
    public ResponseEntity updateUser(@AuthenticationPrincipal User user1, @RequestBody @Valid User newuser){
        authService.updateUser(user1.getUsername(),newuser );

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("User updated "));
    }
    @DeleteMapping("/delete/{username}")
    public ResponseEntity delete(@AuthenticationPrincipal User user, @PathVariable String username){
        authService.delete(username);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("User deleted "));
    }



    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid@ AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body("Welcome back!");

    }


    @PostMapping("/logout")
    public ResponseEntity logout(@RequestBody @Valid @AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body("Done successfully logout!");
    }






}
