package com.dj.stalwart.stalwart.service;

import com.dj.stalwart.stalwart.entity.TimedKeyDetails;
import com.dj.stalwart.stalwart.repo.TimedKeyDetailsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class TimedKeyDetailsService {

    @Autowired
    private TimedKeyDetailsRepository timedKeyDetailsRepository;

    @Transactional
    // save values to the timed key details table
    public TimedKeyDetails save(long ownerId, String ownerType) {
        timedKeyDetailsRepository.updateStatusByOwnerIdAndOwnerType(ownerId, ownerType, "INACTIVE");
        var timedKeyDetailsObj = TimedKeyDetails.builder()
                .ownerId(ownerId)
                .ownerType(ownerType)
                .timedKey(generateOTP())
                .status("ACTIVE")
                .build();
        return timedKeyDetailsRepository.save(timedKeyDetailsObj);
    }

    public TimedKeyDetails findByOwnerIdAndOwnerTypeAndValidTillAfter(long ownerId, String ownerType){
        return timedKeyDetailsRepository.findByOwnerIdAndOwnerTypeAndValidTillAfter(ownerId, ownerType, LocalDateTime.now()).orElse(null);
    }


    private static long generateOTP() {
        Random random = new Random();
        return 100000 + random.nextInt(700000);
    }



}
