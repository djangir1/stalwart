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

    @Column(name = "contactNo", unique = true, nullable = false)
    private long contactNo;

    @Column(name = "roleGroupId")
    private Integer roleGroupId;

    @Column(name = "detailId")
    private long detailId;

    @Column(name = "detailType")
    private String detailType;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "createdTS")
    private LocalDateTime createdTS;

    @Column(name = "updatedTS")
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
