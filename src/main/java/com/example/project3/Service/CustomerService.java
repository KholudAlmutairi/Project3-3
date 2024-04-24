package com.example.project3.Service;

import com.example.project3.Api.ApiException;
import com.example.project3.DTO.CustomerDTO;
import com.example.project3.Model.Customer;
import com.example.project3.Model.User;
import com.example.project3.Repository.AuthRepository;
import com.example.project3.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
  private final CustomerRepository customerRepository;
  private final AuthRepository authRepository;
  private final AuthService authService;

  public void register(CustomerDTO customerDTO){

    String hashPassword = new BCryptPasswordEncoder().encode(customerDTO.getPassword());
    User user = new User(null, customerDTO.getUsername(), hashPassword, customerDTO.getName(), customerDTO.getEmail(), "CUSTOMER", null, null);
    authRepository.save(user);

    Customer customer = new Customer(null, customerDTO.getPhoneNumber(), user, null);
    customerRepository.save(customer);
  }

  public List<Customer> getAllCustomers() {//admin

    return customerRepository.findAll();
  }

//  public Customer addCustomer(CustomerDTO customer) {
//    User user=new User(customer.getUsername(),customer.getPassword(), customer.getName(), customer.getEmail(), customer.getPhoneNumber());
//     authRepository.save(user);
//    // customerRepository.save(customer);
//  }

  public void updateCustomer(String username, CustomerDTO customer) {
    User user=authRepository.findUserByUsername(username);
      if(user==null){
          throw new ApiException("Customer not found with id: ");
      }
       User user1 = new User(customer.getUsername(), customer.getPassword(), customer.getName(), "CUSTOMER", customer.getEmail());
       Customer c = customerRepository.findCustomerByUserCustomer(username);
       c.setPhoneNumber(customer.getPhoneNumber());
       authService.updateUser(username,user);
       //user.setPhoneNumber(customer.getPhoneNumber());
        customerRepository.save(c);
  }

  public void deleteCustomer(String username) { //admin
    User user = authRepository.findUserByUsername(username);
    Customer customer=customerRepository.findCustomerByUserCustomer(username);

    if (user == null) {
      throw new ApiException("Customer Not found");
    }
    authRepository.delete(user);
    customerRepository.delete(customer);
  }




}
