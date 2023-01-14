package com.dh.projetointegradorv1._equipe4.dh_carshop.service;

import com.dh.projetointegradorv1._equipe4.dh_carshop.model.Role;
import com.dh.projetointegradorv1._equipe4.dh_carshop.repository.UserRepository;
import org.eclipse.sisu.plexus.config.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

        @Override
        public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

            com.dh.projetointegradorv1._equipe4.dh_carshop.model.User userAccount = userRepository.findByEmail(email);

            Set<GrantedAuthority> grantList = new HashSet<GrantedAuthority>();

            if (userAccount != null) {
                Role role = userAccount.getFuncao();
                if (role != null) {
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getNome());
                    grantList.add(grantedAuthority);
                    return (UserDetails) new User(email, userAccount.getSenha(), grantList);
                }
            }

            throw new UsernameNotFoundException("Email ou senha incorreto");
        }


}



