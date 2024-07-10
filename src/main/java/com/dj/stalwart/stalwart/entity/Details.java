package com.dj.stalwart.stalwart.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "USER_DETAILS")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Details {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "LOGIN_ID", nullable = false)
    private long loginId;

    @Column(name = "STATUS", nullable = false, length = 20)
    private String status;

    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @Column(name = "GENDER", nullable = false, length = 20)
    private String gender;

    @Column(name = "EMAIL_ID", unique = true, length = 100)
    private String emailId;

    @Column(name = "DOB")
    private LocalDate dob;

    @Column(name = "LINKED_LOGIN_ID")
    private long linkedLoginId;

    @Column(name = "CONTACT_NO")
    private long contactNo;

    @Column(name = "CREATED_TS", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdTS;

    @Column(name = "UPDATED_TS", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedTS;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdTS = now;
        this.updatedTS = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedTS = LocalDateTime.now();
    }
}
