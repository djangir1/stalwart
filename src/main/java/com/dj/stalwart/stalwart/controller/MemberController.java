package com.dj.stalwart.stalwart.controller;

import com.dj.stalwart.stalwart.entity.Details;
import com.dj.stalwart.stalwart.http.response.ActiveUsersResponse;
import com.dj.stalwart.stalwart.http.response.MemberDetails;
import com.dj.stalwart.stalwart.jwt.CustomUserDetails;
import com.dj.stalwart.stalwart.jwt.CustomUserDetailsService;
import com.dj.stalwart.stalwart.service.DetailsRepoService;
import com.dj.stalwart.stalwart.service.LoginService;
import com.dj.stalwart.stalwart.utils.Constants;
import com.dj.stalwart.stalwart.utils.UserDetailsUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/member")
public class MemberController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private DetailsRepoService userDetailsService;

    @Autowired
    private UserDetailsUtils userDetailsUtils;


    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @GetMapping("/getAll")
    public ActiveUsersResponse getAllMembers(HttpServletRequest request) {
        CustomUserDetails loginVal;
        try {
            loginVal = customUserDetailsService.loadUserByToken(request.getHeader("Authorization"));
        } catch (UsernameNotFoundException e) {
            return ActiveUsersResponse.builder()
                    .message("User not found. Please login first")
                    .responseCode(400)
                    .build();
        }
        var loginObj = loginVal.getLogin();
        var linkedUsers = userDetailsService.getAllDetailsByLinkedLoginId(loginObj.getId());
        Details mainUser = null;
        if (Constants.TABLE_TYPES_DETAILS.equals(loginObj.getDetailType())){
            mainUser = userDetailsService.getDetailsByContactNo(loginObj.getContactNo());
            linkedUsers.add(mainUser);
        }

        var activeUsersResponse = userDetailsUtils.getActiveResponse(linkedUsers);

        activeUsersResponse.setMainUser(MemberDetails.builder()
                .id(mainUser.getId())
                .age(userDetailsUtils.getAge(mainUser.getDob()))
                .name(mainUser.getName())
                .emailId(mainUser.getEmailId())
                .contactNo(mainUser.getContactNo())
                .gender(mainUser.getGender())
                .dob(mainUser.getDob())
                .userType(Constants.TABLE_TYPES_DETAILS).build());
        activeUsersResponse.setStatus("Success");
        activeUsersResponse.setMessage("Active users fetched successfully");
        activeUsersResponse.setResponseCode(200);
        return activeUsersResponse;
    }

    @GetMapping("/getLoggedInUser")
    public MemberDetails getLoggedInUserDetails(HttpServletRequest request) {
        CustomUserDetails loginVal;
        try {
            loginVal = customUserDetailsService.loadUserByToken(request.getHeader("Authorization"));
        } catch (UsernameNotFoundException e) {
            return MemberDetails.builder()
                    .message("User not found. Please login first")
                    .responseCode(400)
                    .build();
        }
        var loginObj = loginVal.getLogin();
        Details mainUser = null;
        if (Constants.TABLE_TYPES_DETAILS.equals(loginObj.getDetailType())){
            mainUser = userDetailsService.getDetailsByContactNo(loginObj.getContactNo());
        }
        return MemberDetails.builder()
                .id(loginObj.getId())
                .age(userDetailsUtils.getAge(mainUser.getDob()))
                .name(mainUser.getName())
                .emailId(mainUser.getEmailId())
                .contactNo(mainUser.getContactNo())
                .gender(mainUser.getGender())
                .dob(mainUser.getDob())
                .message("User details fetched successfully")
                .responseCode(200)
                .userType(Constants.TABLE_TYPES_DETAILS).build();
    }
}
