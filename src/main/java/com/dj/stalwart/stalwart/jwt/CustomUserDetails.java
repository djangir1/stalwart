package com.dj.stalwart.stalwart.jwt;

import com.dj.stalwart.stalwart.entity.Login;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data
public class CustomUserDetails implements UserDetails {

    private Login login;

    public CustomUserDetails(Login login) {
        this.login = login;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Implement logic to convert Login's roles/permissions to GrantedAuthority objects
        return Collections.emptyList(); // Return an empty list if there are no roles
    }

    @Override
    public String getPassword() {
        // Return the password, if any
        return null; // Assuming Login does not contain a password
    }

    @Override
    public String getUsername() {
        // Use contactNo as the username
        return String.valueOf(login.getContactNo());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Implement based on your requirements
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Implement based on your requirements
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Implement based on your requirements
    }

    @Override
    public boolean isEnabled() {
        // Implement based on your Login entity's status or similar field
        return "Active".equals(login.getStatus());
    }
}