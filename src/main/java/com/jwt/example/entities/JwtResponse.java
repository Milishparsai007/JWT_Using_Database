package com.jwt.example.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
public class JwtResponse {
    private String jwtToken;
    private String email;
}
