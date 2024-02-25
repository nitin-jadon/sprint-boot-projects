package com.fixedcode.securerestapiilibrary.Security;

import com.fixedcode.securerestapiilibrary.User.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class LibraryUserDetails implements UserDetails {
//user details require 3 parameters: username, password & arrayList of authorities.
    private String userName;
    private String password;
    private List<SimpleGrantedAuthority> authorities;

    public LibraryUserDetails(User user) {//imported our user class code
        userName = user.getEmail();
        password = user.getPassword();
        authorities = Arrays.stream(user.getRole()
                        .split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
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
        return userName;
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
}
