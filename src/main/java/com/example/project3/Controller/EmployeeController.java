package com.example.project3.Controller;

import com.example.project3.Api.ApiResponse;
import com.example.project3.DTO.EmployeeDTO;
import com.example.project3.Model.Employee;
import com.example.project3.Model.User;
import com.example.project3.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {


    private final EmployeeService employeeService;


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid @AuthenticationPrincipal EmployeeDTO employee){
        employeeService.register(employee);
        return ResponseEntity.status(200).body("Employee registered successfully");
    }


    @GetMapping("/get-all")
    public ResponseEntity getAllEmployees() {
        return ResponseEntity.status(200).body(employeeService.getAllEmployees());
    }

    @PutMapping("/update")
    public ResponseEntity updateEmpolyee(@AuthenticationPrincipal User user, @RequestBody @Valid EmployeeDTO employeeDTO){
        employeeService.updateEmployee(user.getUsername(), employeeDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Employee updated successfully"));
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity delete(@AuthenticationPrincipal User user,@PathVariable String username){
        employeeService.deleteEmployee(username);
        return ResponseEntity.status(200).body(new ApiResponse("Employee deleted"));
    }
    @GetMapping("/logout")
    public ResponseEntity logout(){
        return ResponseEntity.status(200).body(new ApiResponse("Logout successfully"));

    }



    @GetMapping("/list-users")
    public ResponseEntity listUsers(){
        return ResponseEntity.status(200).body(employeeService.userList());

    }



    }
