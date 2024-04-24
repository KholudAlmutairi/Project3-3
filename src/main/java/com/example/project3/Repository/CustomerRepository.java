package com.example.project3.Repository;

import com.example.project3.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
 Customer findCustomersById(Integer id);
 Customer findCustomerById(Integer id);


 Customer findCustomersByUser_Customer(String username);

 @Query("select e from Customer e where e.user.username=?1")
 Customer findCustomerByUserCustomer(String username);

}
