package com.dj.stalwart.stalwart.controller;

import com.dj.stalwart.stalwart.entity.DocAppointmentDetails;
import com.dj.stalwart.stalwart.http.request.BookAppointmentRequest;
import com.dj.stalwart.stalwart.http.request.NewPatientDetailsRequest;
import com.dj.stalwart.stalwart.http.response.BookAppointmentResponse;
import com.dj.stalwart.stalwart.http.response.NewPatientDetailsResponse;
import com.dj.stalwart.stalwart.jwt.CustomUserDetails;
import com.dj.stalwart.stalwart.jwt.CustomUserDetailsService;
import com.dj.stalwart.stalwart.service.DetailsRepoService;
import com.dj.stalwart.stalwart.service.DocAppointmentDetailsService;
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
@RequestMapping(value = "/appointment")
public class AppointmentController {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    DocAppointmentDetailsService docAppointmentDetailsService;

    @PostMapping("/add")
    public BookAppointmentResponse addAppointment(HttpServletRequest request, @RequestBody BookAppointmentRequest bookAppointmentRequestBody) {
        Validate.notNull(bookAppointmentRequestBody, "Request body cannot be null");
        CustomUserDetails loginVal = null;
        try {
            loginVal = userDetailsService.loadUserByToken(request.getHeader("Authorization"));
        } catch (UsernameNotFoundException e) {
            return BookAppointmentResponse.builder().status("FAILED").message("User not found. Please login first").responseCode(400).build();
        }
        var loginObj = loginVal.getLogin();

        if (!(bookAppointmentRequestBody.getAppointmentType().equals(Constants.APPOINTMENT_TYPE_ONLINE)
                || bookAppointmentRequestBody.getAppointmentType().equals(Constants.APPOINTMENT_TYPE_OFFLINE))){
            return BookAppointmentResponse.builder().status("FAILED").responseCode(400).message("Invalid appointment type, Please use ONLINE/OFFLINE").build();
        }

        DocAppointmentDetails docAppointmentDetails = DocAppointmentDetails.builder()
                .ownerId(loginObj.getId())
                .bookedUserId(bookAppointmentRequestBody.getUserId())
                .bookedUserType(bookAppointmentRequestBody.getUserType())
                .appointmentType(bookAppointmentRequestBody.getAppointmentType())
                .remarks(bookAppointmentRequestBody.getRemarks())
                .createdBy(loginObj.getId())
                .createdByType(loginObj.getDetailType())
                .status(Constants.APPOINTMENT_STATUS_OPEN)
                .pincode(bookAppointmentRequestBody.getPinCode()).build();

        docAppointmentDetailsService.saveDocAppointmentDetails(docAppointmentDetails);
        return BookAppointmentResponse.builder().status("Success").message("Appointment added successfully").responseCode(200).build();
    }

}
