package com.dj.stalwart.stalwart.http.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDetails {
    private long id;
    private String name;
    private String emailId;
    private long contactNo;
    private String gender;
    private LocalDate dob;
    private String age;
    private String userType;
    private String message;
    private int responseCode;
}
