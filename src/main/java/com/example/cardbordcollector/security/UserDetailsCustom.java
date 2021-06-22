package com.example.cardbordcollector.security;

import com.example.cardbordcollector.model.Role;
import com.example.cardbordcollector.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetailsCustom implements UserDetails {

    private int id;
    private String username;
    private String password;
    private boolean active;
    private List<GrantedAuthority> authorities;

    public UserDetailsCustom(User user) {
        this.id = user.getId();
        this.username = user.getPseudo();
        this.password = user.getPassword();
        this.active = true;

        authorities = new ArrayList<>();

        for(Role role : user.getListeRole()){
            authorities.add(new SimpleGrantedAuthority(role.getDenomination()));
        }

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
