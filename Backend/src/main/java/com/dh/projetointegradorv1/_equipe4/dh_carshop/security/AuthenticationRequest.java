package com.dh.projetointegradorv1._equipe4.dh_carshop.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
public class AuthenticationRequest {
    private String email;
    private String senha;
}