package com.jwt.example.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    //this is the entry point for the authentication.
    //method of this class will be called whenever an unauthenticated person is trying to access the data.
    //commence method will be called.
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); //this is the status which will be set when an unauthorized party accesses the data.
        //prinwriter prints formatted representations of object to a text output stream.
        PrintWriter printWriter=response.getWriter();
        printWriter.println("Access Denied!!"+authException.getMessage());

    }
}
