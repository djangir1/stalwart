package com.dj.stalwart.stalwart.repo;

import com.dj.stalwart.stalwart.entity.DocAppointmentDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocAppointmentDetailsRepo extends CrudRepository<DocAppointmentDetails, Long> {
    List<DocAppointmentDetails> findByOwnerId(long id);
}
