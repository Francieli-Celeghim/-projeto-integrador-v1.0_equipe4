package com.dh.projetointegradorv1._equipe4.dh_carshop.dto;

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
public class RoleDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;
    private List<UserDto> usuarios = new ArrayList<>();

    public RoleDto(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public RoleDto(Role entity) {
        id = entity.getId();
        nome = entity.getNome();
    }

    public RoleDto(Role entity, Set<User> usuarios) {
        this(entity);
        usuarios.forEach(user -> this.usuarios.add(new UserDto(user)));
    }

}
