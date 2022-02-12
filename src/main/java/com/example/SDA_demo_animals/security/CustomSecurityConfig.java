package com.example.SDA_demo_animals.security;

import com.example.SDA_demo_animals.config.Authorities;
import com.example.SDA_demo_animals.config.UserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class CustomSecurityConfig extends WebSecurityConfigurerAdapter {

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
                .antMatchers("**").permitAll()
                .and()
                .csrf().disable();
    }

    @Override
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
    }
}
