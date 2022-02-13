package com.example.SDA_demo_animals.security;

import com.example.SDA_demo_animals.config.Authorities;
import com.example.SDA_demo_animals.config.UserRoles;
import com.example.SDA_demo_animals.repositories.UserRepository;
import com.example.SDA_demo_animals.security.models.User;
import com.example.SDA_demo_animals.security.models.UserAuthority;
import com.example.SDA_demo_animals.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;

@Configuration
public class CustomSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    public void setUserDetailsService(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    private PasswordEncoder encoder;

    @Autowired
    public void setEncoder(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security.httpBasic()
                .and()
                .formLogin()
                .and()
                .logout()
                .and()
                .authorizeRequests().antMatchers("/rest/**").authenticated()
                .and()
                .authorizeRequests().antMatchers("/")
                .hasAnyAuthority(Authorities.SHOW_MAIN_PAGE.getKey(), UserRoles.ADMIN.getAuthority())
                .and()
                .authorizeRequests()
                .antMatchers("/rest/showAllAnimals").authenticated()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/postAnimal").hasRole(UserRoles.ADMIN.getKey())
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/registerUser").not().authenticated()
                .and()
                .authorizeRequests()
                .antMatchers("**").permitAll()
                .and()
                .csrf().disable();
    }

   /* @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password(encoder.encode("1")).roles(UserRoles.ADMIN.getKey())
                .and()
                .withUser("user").password(encoder.encode("1")).roles(UserRoles.USER.getKey())
                .and()
                .withUser("user2").password(encoder.encode("1"))
                .roles(UserRoles.USER.getKey()).authorities(Authorities.SHOW_MAIN_PAGE.getKey())
                .and()
                .withUser("json_user").password(encoder.encode("1")).roles(UserRoles.JSON_USER.getKey());
    }*/

    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return userDetailsService;
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return userDetailsService;
    }

    @PostConstruct
    public void generateAdmin() {
        User user = new User();
        user.setUsername("admin_from_db");
        user.setPassword(encoder.encode("1"));
        user.addAuthority(UserRoles.ADMIN.getAuthority());
        userRepository.save(user);
    }
}
