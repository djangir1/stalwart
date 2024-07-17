package com.dj.stalwart.stalwart.http.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OtpResponse {
    private String status;
    private String message;
    private String userStatus;
    private int responseCode;
}
