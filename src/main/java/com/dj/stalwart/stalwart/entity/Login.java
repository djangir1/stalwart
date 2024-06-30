package com.dj.stalwart.stalwart.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "contactNo", unique = true, nullable = false)
    private long contactNo;

    @Column(name = "roleGroupId")
    private Integer roleGroupId;

    @Column(name = "detailId")
    private Integer detailId;

    @Column(name = "detailType")
    private String detailType;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "createdTS")
    private Timestamp createdTS;

    @Column(name = "updatedTS")
    private Timestamp updatedTS;
}
