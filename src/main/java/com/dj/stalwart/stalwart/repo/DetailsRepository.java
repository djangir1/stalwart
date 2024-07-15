package com.dj.stalwart.stalwart.repo;

import com.dj.stalwart.stalwart.entity.Details;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailsRepository extends CrudRepository<Details, Integer> {

    Details findByContactNo(long contactNo);

    List<Details> findByLinkedLoginId(long linkedLoginId);
}
