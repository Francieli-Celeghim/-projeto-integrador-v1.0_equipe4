package com.dh.projetointegradorv1._equipe4.dh_carshop.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig<MyUserDetailsService, JwtRequestFilter> extends WebSecurityConfigurerAdapter {

    @Autowired
    private com.dh.projetointegradorv1._equipe4.dh_carshop.service.MyUserDetailsService myUserDetailsService;

    @Autowired
    private com.dh.projetointegradorv1._equipe4.dh_carshop.security.JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/v1/users/**").permitAll() // nao pode exigir jwt
                .antMatchers(HttpMethod.GET, "/api/v1/users/email/**").permitAll()
                .antMatchers(HttpMethod.GET,"/api/v1/bookings/**").permitAll() // deve exigir jwt
                .antMatchers(HttpMethod.GET,"/api/v1/categories/**").permitAll()
                .antMatchers(HttpMethod.GET,"/api/v1/cities/**").permitAll()
                .antMatchers(HttpMethod.GET,"/api/v1/features/**").permitAll()
                .antMatchers(HttpMethod.GET,"/api/v1/products/**").permitAll()
                .antMatchers(HttpMethod.POST,"/api/v1/products/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/api/v1/product/**").permitAll()
                .antMatchers("/api/v1/roles/**").permitAll()
                .antMatchers("/authenticate/**").permitAll() // nao pode exigir jwt
                .anyRequest()
                .authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /*@Bean
    CorsConfigurationSource corsConfigurationSource() {
       org.springframework.web.cors.CorsConfiguration configuration = new org.springframework.web.cors.CorsConfiguration().applyPermitDefaultValues();
        configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }*/

}
