package com.dj.stalwart.stalwart.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TIMED_KEY_DETAILS")
public class TimedKeyDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "OWNER_ID", nullable = false)
    private long ownerId;

    @Column(name = "OWNER_TYPE", nullable = false, length = 20)
    private String ownerType;

    @Column(name = "TIMED_KEY", nullable = false)
    private long timedKey;

    @Column(name = "VALID_TILL", nullable = false)
    private LocalDateTime validTill;

    @Column(name = "CREATED_TS")
    private LocalDateTime createdTs;

    @Column(name = "STATUS", nullable = false, length = 20)
    private String status;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdTs = now;
        this.validTill = now.plusMinutes(5);
    }

}

