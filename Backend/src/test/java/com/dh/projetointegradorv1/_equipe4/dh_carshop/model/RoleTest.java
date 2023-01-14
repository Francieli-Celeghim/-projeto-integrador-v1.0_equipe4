package com.dh.projetointegradorv1._equipe4.dh_carshop.model;

import com.dh.projetointegradorv1._equipe4.dh_carshop.dto.RoleDto;
import com.dh.projetointegradorv1._equipe4.dh_carshop.dto.UserDto;
import com.dh.projetointegradorv1._equipe4.dh_carshop.service.RoleService;
import com.dh.projetointegradorv1._equipe4.dh_carshop.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class RoleTest {

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Test
    void createRole() {
        //cenário
        RoleDto role = new RoleDto();
        Role entity = new Role();

        // expectativas
        String nome = "Admin";

        // teste de get e set de nome
        role.setNome(nome);
        Assertions.assertEquals(nome, role.getNome());

        // create
        role = roleService.createRole(role);

        // aqui esta sendo testado a existencia do id e inclusive que foi salvo no banco porque tem um id
        Assertions.assertInstanceOf(java.lang.Integer.class, role.getId());

        role.setId(0);
        Assertions.assertEquals(0, role.getId());

        // testando se o initializer User() funcionou
        Assertions.assertEquals(nome, role.getNome());

        role.setId(0);
        Assertions.assertEquals(0, role.getId());

    }

    private UserDto getUsuarios() {
        return userService.findUserById(1);
    }

}