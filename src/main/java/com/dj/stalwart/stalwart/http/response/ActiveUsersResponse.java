package com.dj.stalwart.stalwart.http.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActiveUsersResponse {
    private List<MemberDetails> members;
    private MemberDetails mainUser;
    private String status;
    private String message;
    private int responseCode;
}
