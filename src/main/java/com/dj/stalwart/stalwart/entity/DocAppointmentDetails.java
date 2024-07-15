package com.dj.stalwart.stalwart.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "DOC_APPOINTMENT_DETAILS")
public class DocAppointmentDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "OWNER_ID", nullable = false)
    private Long ownerId;

    @Column(name = "BOOKED_USER_ID", nullable = false)
    private Long bookedUserId;

    @Column(name = "BOOKED_USER_TYPE", nullable = false, length = 100)
    private String bookedUserType;

    @Column(name = "DOC_ID")
    private Long docId;

    @Column(name = "PINCODE")
    private Long pincode;

    @Column(name = "APPOINTMENT_TYPE", nullable = false, length = 20)
    private String appointmentType;

    @Column(name = "REMARKS", length = 150)
    private String remarks;

    @Column(name = "TRANSACTION_ID")
    private Long transactionId;

    @Column(name = "SLOT_TS")
    private Timestamp slotTs;

    @Column(name = "STATUS", nullable = false, length = 20)
    private String status;

    @Column(name = "CREATED_BY", nullable = false)
    private Long createdBy;

    @Column(name = "CREATED_BY_TYPE", nullable = false, length = 20)
    private String createdByType;

    @Column(name = "CREATED_TS", nullable = false)
    private LocalDateTime createdTs;

    @Column(name = "UPDATED_TS", nullable = false)
    private LocalDateTime updatedTs;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdTs = now;
        this.updatedTs = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedTs = LocalDateTime.now();
    }

}
