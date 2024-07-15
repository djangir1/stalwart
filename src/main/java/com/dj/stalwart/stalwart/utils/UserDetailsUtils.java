package com.dj.stalwart.stalwart.utils;

import com.dj.stalwart.stalwart.entity.Details;
import com.dj.stalwart.stalwart.http.response.ActiveUsersResponse;
import com.dj.stalwart.stalwart.http.response.MemberDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDetailsUtils {

    public ActiveUsersResponse getActiveResponse(List<Details> detailsList) {
        List<MemberDetails> memberDetailsList = detailsList.stream().map(detail -> MemberDetails.builder()
                        .id(detail.getId())
                        .age(getAge(detail.getDob()))
                        .name(detail.getName())
                        .emailId(detail.getEmailId())
                        .contactNo(detail.getContactNo())
                        .gender(detail.getGender())
                        .dob(detail.getDob())
                        .userType(Constants.TABLE_TYPES_DETAILS).build()
                // Map other fields as necessary
        ).collect(Collectors.toList());

        return ActiveUsersResponse.builder()
                .members(memberDetailsList)
                .build();

    }

    public String getAge(LocalDate dob) {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(dob, currentDate);
        return String.format("%d years %d months", period.getYears(), period.getMonths());
    }
}





/*
    private long id;
    private long loginId;

    private String status;
    private String name;
    private String gender;
    private String emailId;
    private LocalDate dob;
    private long linkedLoginId;
    private long contactNo;
    private LocalDateTime createdTS;
    private LocalDateTime updatedTS;


private long id;
private String name;
private String emailId;
private long contactNo;
private String gender;
private LocalDate dob;
private String age;

*/
