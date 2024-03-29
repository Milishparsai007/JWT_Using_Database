package com.jwt.example.controllers;

import com.jwt.example.entities.User;
import com.jwt.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public List<User> getUsers()
    {
        System.out.println("This is list of users");
        return this.userService.getAllUsers();
    }

    @GetMapping("/currentUser")
    String getLoggedInUser(Principal principal)
    {
        //this will return the name of user that is logged in.
        //principal ek esa object hota hai jo hmare current subject ko represent krta hai jo ki login hai.
        return principal.getName();
    }


}
