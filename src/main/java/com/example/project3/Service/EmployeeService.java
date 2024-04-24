package com.example.project3.Service;

import com.example.project3.Api.ApiException;
import com.example.project3.DTO.EmployeeDTO;
import com.example.project3.Model.Account;
import com.example.project3.Model.Customer;
import com.example.project3.Model.Employee;
import com.example.project3.Model.User;
import com.example.project3.Repository.AccountRepository;
import com.example.project3.Repository.AuthRepository;
import com.example.project3.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final AuthRepository authRepository;
    private final AccountRepository accountRepository;
    private final AuthService authService;



    public void register(EmployeeDTO employeeDTO){

        String hashPassword = new BCryptPasswordEncoder().encode(employeeDTO.getPassword());
        User user = new User(null, employeeDTO.getUsername(), hashPassword, employeeDTO.getName(), employeeDTO.getEmail(), "EMPLOYEE", null, null);
        authRepository.save(user);

        Employee employee = new Employee(null, employeeDTO.getPosition(),employeeDTO.getSalary(), user);//employeeDTO.getSalary()
        employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {//admin
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Integer id) {
      return employeeRepository.findEmployeesById(id);
    }



    public void updateEmployee(String username, EmployeeDTO employeeDTO) {
        User user = authRepository.findUserByUsername(username);
        if (user == null) {
            throw new ApiException("Employee Not found");
        }
        User user1 = new User(employeeDTO.getUsername(), employeeDTO.getPassword(), employeeDTO.getName(), "EMPLOYEE", employeeDTO.getEmail());
        Employee employee = employeeRepository.findEmployeesByUsername(username);
        employee.setPosition(employeeDTO.getPosition());
        employee.setSalary(employeeDTO.getSalary());

        authService.updateUser(username,user);
        employeeRepository.save(employee);
    }


//    public Employee updateEmployee(Integer id, Employee employee) {
//        Employee e = employeeRepository.findEmployeesById(id);
//         if(e==null)
//            throw new ApiException("Employee not found with id: ");
//
//            e.setPosition(employee.getPosition());
//            e.setSalary(employee.getSalary());
//            return employeeRepository.save(e);
//    }

    public void deleteEmployee(String username) {

        User user = authRepository.findUserByUsername(username);
        Employee employee = employeeRepository.findEmployeesByUsername(username);

        if (user == null) {
            throw new ApiException("Employee Not found");
        }

        authRepository.delete(user);
        employeeRepository.delete(employee);

    }


    public List<User> userList(){

        return authRepository.findAll();
    }







    }








