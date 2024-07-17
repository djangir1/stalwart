package com.dj.stalwart.stalwart.repo;

import com.dj.stalwart.stalwart.entity.TimedKeyDetails;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface TimedKeyDetailsRepository extends CrudRepository<TimedKeyDetails, Long> {


    @Query("SELECT tk FROM TimedKeyDetails tk WHERE tk.ownerId = :ownerId AND tk.status='ACTIVE' AND tk.ownerType = :ownerType AND tk.validTill >= :now")
    Optional<TimedKeyDetails> findByOwnerIdAndOwnerTypeAndValidTillAfter(long ownerId, String ownerType, LocalDateTime now);

    @Modifying
    @Query("UPDATE TimedKeyDetails tk SET tk.status = :status WHERE tk.ownerId = :ownerId AND tk.ownerType = :ownerType")
    int updateStatusByOwnerIdAndOwnerType(long ownerId, String ownerType, String status);
}
