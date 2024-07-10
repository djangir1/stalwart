package com.dj.stalwart.stalwart.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "CONTACT_NO", unique = true, nullable = false)
    private long contactNo;

    @Column(name = "ROLE_GROUP_ID")
    private Integer roleGroupId;

    @Column(name = "DETAIL_ID")
    private long detailId;

    @Column(name = "DETAIL_TYPE")
    private String detailType;

    @Column(name = "STATUS", nullable = false)
    private String status;

    @Column(name = "CREATED_TS")
    private LocalDateTime createdTS;

    @Column(name = "UPDATED_TS")
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
