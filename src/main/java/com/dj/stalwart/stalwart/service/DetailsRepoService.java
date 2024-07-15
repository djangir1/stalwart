package com.dj.stalwart.stalwart.service;

import com.dj.stalwart.stalwart.entity.Details;
import com.dj.stalwart.stalwart.repo.DetailsRepository;
import com.dj.stalwart.stalwart.utils.Constants;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailsRepoService {

    @Autowired
    private DetailsRepository detailsRepository;

    @Autowired
    private LoginService loginService;

    public Details saveDetails(Details details) {
        return detailsRepository.save(details);
    }

    public void getDetailsById(int id) {
        detailsRepository.findById(id).get();
    }

    public Details getDetailsByContactNo(long contactNo) {
        return detailsRepository.findByContactNo(contactNo);
    }

    public List<Details> getAllDetailsByLinkedLoginId(long linkedLoginId) {
        return detailsRepository.findByLinkedLoginId(linkedLoginId);
    }

    @Transactional
    public Details saveDetailsWithLoginInfoUpdate(Details details, long loginId) {
        var detailsFromDB = saveDetails(details);
        loginService.updateLoginStatus(loginId, Constants.LOGIN_STATUS_ACTIVE, detailsFromDB.getId(), Constants.TABLE_TYPES_DETAILS);
        return detailsFromDB;
    }
}