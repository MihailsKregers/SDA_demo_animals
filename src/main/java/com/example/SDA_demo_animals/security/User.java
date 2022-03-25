package com.example.SDA_demo_animals.security;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class User {

    @Id
    private String username;
    private String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<UserAuthority> authorities;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<UserAuthority> getAuthorities() {
        if (this.authorities == null) {
            this.authorities = new ArrayList<UserAuthority>();
        }
        return authorities;
    }

    public void setAuthorities(Collection<UserAuthority> authorities) {
        this.authorities = authorities;
    }

    public void addAuthority(String authority) {
        UserAuthority userAuthority = new UserAuthority();
        userAuthority.setUser(this);
        userAuthority.setAuthority(authority);
        this.getAuthorities().add(userAuthority);
    }
}
