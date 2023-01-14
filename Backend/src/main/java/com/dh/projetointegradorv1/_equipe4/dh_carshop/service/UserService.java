package com.dh.projetointegradorv1._equipe4.dh_carshop.service;


import com.dh.projetointegradorv1._equipe4.dh_carshop.dto.BookingDto;
import com.dh.projetointegradorv1._equipe4.dh_carshop.dto.UserDto;
import com.dh.projetointegradorv1._equipe4.dh_carshop.model.*;
import com.dh.projetointegradorv1._equipe4.dh_carshop.repository.BookingRepository;
import com.dh.projetointegradorv1._equipe4.dh_carshop.repository.RoleRepository;
import com.dh.projetointegradorv1._equipe4.dh_carshop.repository.UserRepository;
import com.dh.projetointegradorv1._equipe4.dh_carshop.service.exceptions.BDExcecao;
import com.dh.projetointegradorv1._equipe4.dh_carshop.service.exceptions.RecursoNaoEncontrado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Optional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Transactional
    public UserDto createUser(UserDto dto) {
        User entity = new User();
        dto.setSenha(encoder.encode(dto.getSenha()));
        copyToEntity(dto, entity);
        entity = userRepository.save(entity);
        return new UserDto(entity);
    }

    @Transactional(readOnly = true)
    public List<UserDto> listAllUsers() {
        List<UserDto> listDto = new ArrayList<>();
        List<User> list = userRepository.findAll();
        for(User user : list) {
            UserDto dto = new UserDto(user);
            listDto.add(dto);
        }
        return listDto;
    }

    @Transactional(readOnly = true)
    public UserDto findUserById(Integer id) {
        Optional<User> obj = userRepository.findById(id);
        User entity = obj.orElseThrow(() -> new RecursoNaoEncontrado("ENTIDADE NÃO ENCONTRADA"));
        return new UserDto(entity, entity.getFuncao(), entity.getReservas());
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public UserDto updateUserById(Integer id, UserDto dto) {
        try {
            Optional<User> obj = userRepository.findById(id);
            User entity = obj.orElseThrow(() -> new RecursoNaoEncontrado("ENTIDADE NÃO ENCONTRADA"));
            copyToEntity(dto, entity);
            entity = userRepository.save(entity);
            return new UserDto(entity);
        }
        catch (EntityNotFoundException e) {
            throw new RecursoNaoEncontrado("ID NÃO ENCONTRADO: " + id);
        }

        /*return userRepository.findById(id)
                .map(userToUpdate -> {
                    userToUpdate.setNome(user.getNome());
                    userToUpdate.setSobrenome(user.getSobrenome());
                    userToUpdate.setEmail(user.getEmail());
                    userToUpdate.setSenha(user.getSenha());
                    return userRepository.save(userToUpdate);
                }).orElseGet(() -> {
                    user.setId(id);
                    return userRepository.save(user);
                });*/
    }

    public void deleteUserById(Integer id) {
        try{
            userRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new RecursoNaoEncontrado("ID NÃO ENCONTRADO: " + id);
        }
        catch (DataIntegrityViolationException e) {
            throw new BDExcecao("VIOLAÇÃO DE INTEGRIDADE");
        }
    }

    public void copyToEntity(UserDto dto, User entity) {
        entity.setNome(dto.getNome());
        entity.setSobrenome(dto.getSobrenome());
        entity.setEmail(dto.getEmail());
        entity.setSenha(dto.getSenha());
        for(BookingDto bookDto : dto.getReservas()) {
            Optional<Booking> obj = bookingRepository.findById(bookDto.getId());
            Booking booking = obj.orElseThrow(() -> new RecursoNaoEncontrado("ENTIDADE NÃO ENCONTRADA"));
            entity.getReservas().add(booking);
        }
        System.out.println("====dto.getFuncao()");
        System.out.println(dto.getFuncao());
        System.out.println("====dto.getFuncao().getId()");
        System.out.println(dto.getFuncao().getId());
        Optional<Role> obj = roleRepository.findById(dto.getFuncao().getId());
        Role role = obj.orElseThrow(() -> new RecursoNaoEncontrado("ENTIDADE NÃO ENCONTRADA"));
        entity.setFuncao(role);
    }

}
