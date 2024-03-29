package com.jwt.example.service;

import com.jwt.example.entities.User;
import com.jwt.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService{
    @Autowired
    private UserRepository userRepository;
//    List<User> userList=new ArrayList<>();
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

//    public UserServiceImpl()
//    {
//        userList.add(new User(UUID.randomUUID().toString(),"Milish Parsai","mparsai@gmail.com"));
//        userList.add(new User(UUID.randomUUID().toString(),"Bhavya Jain","bjain@gmail.com"));
//        userList.add(new User(UUID.randomUUID().toString(),"Dhannu Joshi","Djoshi@gmail.com"));
//    }


    public User createUser(User user)
    {
        user.setUserId(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
