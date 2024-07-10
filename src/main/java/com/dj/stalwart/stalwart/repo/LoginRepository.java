package com.dj.stalwart.stalwart.repo;

import com.dj.stalwart.stalwart.entity.Login;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface LoginRepository extends CrudRepository<Login, Integer> {

    Login findByContactNo(long contactNo);

    @Modifying
    @Query("UPDATE Login l SET l.status = :status, l.detailId = :detailId, l.detailType = :detailType WHERE l.id = :loginId")
    int updateLoginStatusAndDetailsById(long loginId, String status, long detailId, String detailType);
}
