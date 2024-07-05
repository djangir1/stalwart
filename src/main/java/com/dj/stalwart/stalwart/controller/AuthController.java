package com.dj.stalwart.stalwart.controller;

import com.dj.stalwart.stalwart.entity.Login;
import com.dj.stalwart.stalwart.http.request.JwtRequest;
import com.dj.stalwart.stalwart.http.response.JwtResponse;
import com.dj.stalwart.stalwart.jwt.CustomUserDetails;
import com.dj.stalwart.stalwart.jwt.CustomUserDetailsService;
import com.dj.stalwart.stalwart.jwt.JwtHelper;
import com.dj.stalwart.stalwart.service.LoginService;
import com.dj.stalwart.stalwart.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtHelper jwtHelper;
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
        CustomUserDetails loginVal = null;
        try {
            loginVal = userDetailsService.loadUserByUsername(request.getContactNo() + "");
        } catch (UsernameNotFoundException e) {
            var newLogin = Login.builder().contactNo(request.getContactNo()).status(Constants.LOGIN_STATUS_NEW).roleGroupId(1).detailId(-1).build();
            loginVal = new CustomUserDetails(newLogin);
            loginService.saveLogin(newLogin);
        }
        String token = this.jwtHelper.generateToken(request.getContactNo());
        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .contactNo(request.getContactNo())
                .status(loginVal.getLogin().getStatus()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }


}
