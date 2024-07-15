package com.dj.stalwart.stalwart.jwt;

import com.dj.stalwart.stalwart.entity.Login;
import com.dj.stalwart.stalwart.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private LoginService loginService;

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public CustomUserDetails loadUserByUsername(String contactNo) throws UsernameNotFoundException {
        Login login = loginService.getLoginByContactNo(Long.parseLong(contactNo));
        if (login == null) {
            throw new UsernameNotFoundException("User not found with contact number: " + contactNo);
        }
        return new CustomUserDetails(login);
    }

    public CustomUserDetails loadUserByToken(String token) throws UsernameNotFoundException {
        String contactNoStr = jwtHelper.extractUserNameByBererToken(token) + "";
        return loadUserByUsername(contactNoStr);
    }
}