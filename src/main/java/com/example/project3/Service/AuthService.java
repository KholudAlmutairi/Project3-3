package com.example.project3.Service;

import com.example.project3.Api.ApiException;
import com.example.project3.Model.User;
import com.example.project3.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;


    public List<User> getAllUsers() {// admin
        return authRepository.findAll();
    }


    public void addUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        authRepository.save(user);
    }

    public void updateUser(String username, User user) {
        User user1 = authRepository.findUserByUsername(username);
        if (user1 == null) {
            throw new ApiException("User Not found");
        }
        user1.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user1.setEmail(user.getEmail());
        user1.setRole(user.getRole());
        user1.setName(user.getName());
        user1.setUsername(user.getUsername());
        authRepository.save(user1);

    }

    public void delete(String username) {
        User user = authRepository.findUserByUsername(username);

        if (user == null) {
            throw new ApiException("Employee Not found");
        }
        authRepository.delete(user);



    }






//    public User getUserById(Integer userId){
//        return authRepository.findUserById(userId);
//    }


//    public void deleteUser(String username1,String username2) {//admin
//        User u = authRepository.findUserByUsername(username1);
//        if (!u.getRole().equalsIgnoreCase("ADMIN")) {
//            throw new ApiException("Admin cannot be deleted");
//        }
//        User u2=authRepository.findUserByUsername(username1);
//        authRepository.delete(u2);
//    }


  // ما ضبطت
//    public void updateUser(Integer id,User user) {
//        user.setRole("CUSTOMER");
//        User u = authRepository.findUserById(id);
//
//        if (u == null) {
//            throw new ApiException("Wrong id");
//        }
//        u.setUsername(user.getUsername());
//        u.setPassword(user.getPassword());
//        authRepository.save(u);
//
//
//    }

}
