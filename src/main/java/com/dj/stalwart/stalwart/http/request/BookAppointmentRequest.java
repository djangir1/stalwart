package com.dj.stalwart.stalwart.http.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookAppointmentRequest {
    private long userId;
    private LocalDate date;
    private String time;
    private String symptoms;
    private long pinCode;
    private String type;
    private String userType;
    private String appointmentType; // online/ Offline
    private String remarks;
}
