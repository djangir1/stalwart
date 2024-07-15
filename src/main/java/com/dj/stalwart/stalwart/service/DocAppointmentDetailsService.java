package com.dj.stalwart.stalwart.service;

import com.dj.stalwart.stalwart.entity.DocAppointmentDetails;
import com.dj.stalwart.stalwart.repo.DocAppointmentDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocAppointmentDetailsService {

    @Autowired
    DocAppointmentDetailsRepo docAppointmentDetailsRepo;

    public void saveDocAppointmentDetails(DocAppointmentDetails docAppointmentDetails) {
        docAppointmentDetailsRepo.save(docAppointmentDetails);
    }

    public DocAppointmentDetails getDocAppointmentDetailsById(long id) {
        return docAppointmentDetailsRepo.findById(id).get();
    }

    public List<DocAppointmentDetails> getAllDocAppointmentsByOwnerId(long ownerId) {
        return docAppointmentDetailsRepo.findByOwnerId(ownerId);
    }

}
