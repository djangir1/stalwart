package com.dj.stalwart.stalwart.controller;

import com.dj.stalwart.stalwart.entity.Details;
import com.dj.stalwart.stalwart.http.request.NewPatientDetailsRequest;
import com.dj.stalwart.stalwart.http.response.NewPatientDetailsResponse;
import com.dj.stalwart.stalwart.jwt.CustomUserDetails;
import com.dj.stalwart.stalwart.jwt.CustomUserDetailsService;
import com.dj.stalwart.stalwart.jwt.JwtHelper;
import com.dj.stalwart.stalwart.service.DetailsRepoService;
import com.dj.stalwart.stalwart.service.LoginService;
import com.dj.stalwart.stalwart.utils.Constants;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/patient")
public class PatientController {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private DetailsRepoService detailsRepoService;

    @Autowired
    private JwtHelper jwtHelper;

    @PostMapping("/addDetails")
    public @ResponseBody NewPatientDetailsResponse addPatient(HttpServletRequest request, @RequestBody NewPatientDetailsRequest newPatientRequestBody) {
        Validate.notNull(newPatientRequestBody, "Request body cannot be null");
        CustomUserDetails loginVal = null;
        try {
            loginVal = userDetailsService.loadUserByToken(request.getHeader("Authorization"));
        } catch (UsernameNotFoundException e) {
            return NewPatientDetailsResponse.builder()
                    .message("User not found. Please login first")
                    .responseCode(400)
                    .build();
        }
        var loginObj = loginVal.getLogin();

        if (!Constants.LOGIN_STATUS_NEW.equals(loginObj.getStatus())) {
            return NewPatientDetailsResponse.builder()
                    .message("User already exists")
                    .responseCode(400)
                    .userStatus(loginObj.getStatus())
                    .build();
        }

        Details details = Details.builder()
                .contactNo(loginObj.getContactNo())
                .loginId(loginObj.getId())
                .status(Constants.LOGIN_STATUS_ACTIVE)
                .name(newPatientRequestBody.getName())
                .gender(newPatientRequestBody.getGender())
                .emailId(newPatientRequestBody.getEmailId())
                .dob(newPatientRequestBody.getDob())
                .linkedLoginId(-1)
                .contactNo(loginObj.getContactNo())
                .build();

        var detailsFromDB = detailsRepoService.saveDetailsWithLoginInfoUpdate(details, loginObj.getId());

        return NewPatientDetailsResponse.builder()
                .message("Patient details added successfully")
                .responseCode(200)
                .userStatus(detailsFromDB.getStatus())
                .build();

    }
}
