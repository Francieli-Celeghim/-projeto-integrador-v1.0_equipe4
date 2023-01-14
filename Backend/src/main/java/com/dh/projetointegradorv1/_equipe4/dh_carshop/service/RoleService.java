package com.dh.projetointegradorv1._equipe4.dh_carshop.service;

import com.dh.projetointegradorv1._equipe4.dh_carshop.dto.ProductDto;
import com.dh.projetointegradorv1._equipe4.dh_carshop.dto.RoleDto;
import com.dh.projetointegradorv1._equipe4.dh_carshop.dto.UserDto;
import com.dh.projetointegradorv1._equipe4.dh_carshop.model.Product;
import com.dh.projetointegradorv1._equipe4.dh_carshop.model.Role;
import com.dh.projetointegradorv1._equipe4.dh_carshop.model.User;
import com.dh.projetointegradorv1._equipe4.dh_carshop.repository.RoleRepository;
import com.dh.projetointegradorv1._equipe4.dh_carshop.repository.UserRepository;
import com.dh.projetointegradorv1._equipe4.dh_carshop.service.exceptions.RecursoNaoEncontrado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public RoleDto createRole(RoleDto dto) {
        Role entity = new Role();
        copyToEntity(dto, entity);
        entity = roleRepository.save(entity);
        return new RoleDto(entity);
    }

    @Transactional(readOnly = true)
    public List<RoleDto> listAllRoles() {
        List<RoleDto> listDto = new ArrayList<>();
        List<Role> list = roleRepository.findAll();
        for(Role role : list) {
            RoleDto dto = new RoleDto(role);
            listDto.add(dto);
        }
        return listDto;
    }

    @Transactional(readOnly = true)
    public RoleDto findRoleById(Integer id) {
        Optional<Role> obj = roleRepository.findById(id);
        Role entity = obj.orElseThrow(() -> new RecursoNaoEncontrado("ENTIDADE NÃO ENCONTRADA"));
        return new RoleDto(entity, entity.getUsuarios());
    }

    public void copyToEntity(RoleDto dto, Role entity) {
        entity.setNome(dto.getNome());
        entity.getUsuarios().clear();
        for(UserDto userDto : dto.getUsuarios()) {
            Optional<User> obj = userRepository.findById(userDto.getId());
            User user = obj.orElseThrow(() -> new RecursoNaoEncontrado("ENTIDADE NÃO ENCONTRADA"));
            entity.getUsuarios().add(user);
        }
    }

}
