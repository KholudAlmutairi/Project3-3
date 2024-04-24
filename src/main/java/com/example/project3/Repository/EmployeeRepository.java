package com.example.project3.Repository;

import com.example.project3.Model.Employee;
import com.example.project3.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
 Employee findEmployeesById(Integer id);

 @Query("select e from Employee e where e.user.username=?1")
 Employee findEmployeesByUsername(String username);

 Employee findEmployeesByUserEmployee(User user);




}
