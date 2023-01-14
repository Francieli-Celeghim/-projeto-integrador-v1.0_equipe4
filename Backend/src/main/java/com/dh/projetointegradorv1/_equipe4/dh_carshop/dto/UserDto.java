package com.dh.projetointegradorv1._equipe4.dh_carshop.dto;

import com.dh.projetointegradorv1._equipe4.dh_carshop.model.Booking;
import com.dh.projetointegradorv1._equipe4.dh_carshop.model.Role;
import com.dh.projetointegradorv1._equipe4.dh_carshop.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter @Setter @NoArgsConstructor
public class UserDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;
    private String sobrenome;
    private String email;
    private String senha;
    private RoleDto funcao;
    private List<BookingDto> reservas = new ArrayList<>();

    public UserDto(Integer id, String nome, String sobrenome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.senha = senha;
    }

    public UserDto(User entity) {
        id = entity.getId();
        nome = entity.getNome();
        sobrenome = entity.getSobrenome();
        email = entity.getEmail();
        senha = entity.getSenha();
    }

    public UserDto(User entity, Role funcao, Set<Booking> reservas) {
        this(entity);
        this.funcao = new RoleDto(funcao);
        reservas.forEach(book -> this.reservas.add(new BookingDto(book)));
    }

}
