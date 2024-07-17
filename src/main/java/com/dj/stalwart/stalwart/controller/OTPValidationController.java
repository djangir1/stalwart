package com.dj.stalwart.stalwart.controller;

import com.dj.stalwart.stalwart.http.request.OtpRequest;
import com.dj.stalwart.stalwart.http.response.OtpResponse;
import com.dj.stalwart.stalwart.jwt.CustomUserDetails;
import com.dj.stalwart.stalwart.jwt.CustomUserDetailsService;
import com.dj.stalwart.stalwart.service.TimedKeyDetailsService;
import com.dj.stalwart.stalwart.utils.Constants;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/validate-otp")
public class OTPValidationController {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private TimedKeyDetailsService timedKeyDetailsService;

    @PostMapping("/confirm")
    public OtpResponse confirmOTP(HttpServletRequest request, @RequestBody OtpRequest otpRequest) {
        Validate.notNull(otpRequest, "Request body cannot be null");
        CustomUserDetails loginVal = null;
        try {
            loginVal = userDetailsService.loadUserByToken(request.getHeader("Authorization"));
        } catch (UsernameNotFoundException e) {
            return OtpResponse.builder().status("FAILED").message("User not found. Please login first").responseCode(400).build();
        }
        var loginObj = loginVal.getLogin();
        var otpValidationDBRec = timedKeyDetailsService.findByOwnerIdAndOwnerTypeAndValidTillAfter(loginObj.getId(), Constants.TABLE_TYPES_LOGIN);
        if (otpValidationDBRec == null) {
            return OtpResponse.builder().status("FAILED").message("OTP expired or not generated, Please try again").responseCode(400).build();
        }
        if (otpValidationDBRec.getTimedKey()==otpRequest.getOtp()) {
            return OtpResponse.builder().status("SUCCESS").message("OTP validated successfully").userStatus(loginObj.getStatus()).responseCode(200).build();
        } else {
            return OtpResponse.builder().status("INVALID-OTP").message("Incorrect OTP. Please check and insert again.").responseCode(400).build();
        }
    }
}
