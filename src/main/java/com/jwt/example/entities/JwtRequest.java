package com.jwt.example.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
public class JwtRequest {
    private String email;
    private String password;
}
