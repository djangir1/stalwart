package com.dj.stalwart.stalwart.http.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewPatientDetailsResponse {
    private String response;
    private String message;
    private int responseCode;
    private String userStatus;
}
