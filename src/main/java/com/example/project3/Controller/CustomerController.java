package com.example.project3.Controller;

import com.example.project3.Api.ApiResponse;
import com.example.project3.DTO.CustomerDTO;
import com.example.project3.Model.Customer;
import com.example.project3.Model.User;
import com.example.project3.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid CustomerDTO customer) {
        customerService.register(customer);
        return ResponseEntity.status(200).body("Customer registered successfully");
    }

    @GetMapping("/get-all")
    public ResponseEntity getAllCustomers() {

        return ResponseEntity.status(200).body(customerService.getAllCustomers()) ;
    }


    @PutMapping("/update")
    public ResponseEntity updateCustomer(@AuthenticationPrincipal User user, @RequestBody @Valid CustomerDTO customer) {
        customerService.updateCustomer(user.getUsername(),customer);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Customer updated "));
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteCustomer(@AuthenticationPrincipal User user) {

        customerService.deleteCustomer(user.getUsername());
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Customer deleted "));
    }
}
