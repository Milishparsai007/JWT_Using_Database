package com.jwt.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class AppCOnfig {

    //this is a predefined interface in spring security.
    //below code was used when we were not useing database, instead we were using in-memory based authentication.
    //ab hoga CustomUserDetailService ka use jo ki database se user ko fetch kregi.
//    @Bean
//    public UserDetailsService userDetailsService()
//    //ye UserDetailService calll hoti hai users ko load krne ke liye for the authentication process.
//    {
//        //is UserDetailService me ek method hai loaduserbyusername krke.
//        //wo method call hoti hai jab koi user access kr rha ho api ko tab.
//        //to ab jab wo method call hogi to hmare dwara niche created users load honge.
//                            //this user is from the springsecurity and not from our created user.
//        UserDetails user1 = User.builder().username("Milish").password(passwordEncoder().encode("mp@123")).roles("ADMIN").build();
//        UserDetails user2 = User.builder().username("Dhannu").password(passwordEncoder().encode("dj@123")).roles("ADMIN").build();
//
//
//        //this is a way to create in memory username and password without using database.
//        //we can pass as many user as we want because this below method has varargs in its argument.
//        return new InMemoryUserDetailsManager(user1,user2);
//
//
//        //ye saari userdetails, User, userdetailsservice ye sb spring security ke andar by default aati hai.
//        //inme jo actual user hai usse related info stored rehti hai mtlb ki jo user access krega hmari apis ko
//        //uski details rehti hai and hum custom set bhi karskte hai uski help se aur even hmare database me jo users
//        //stored hai unko bhi hum set kr skte hai access and everything using these classes and intefaces.
//    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {

        return builder.getAuthenticationManager();

    }
}
