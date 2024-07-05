package com.dj.stalwart.stalwart.repo;

import com.dj.stalwart.stalwart.entity.Login;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends CrudRepository<Login, Integer> {

    Login findByContactNo(long contactNo);

}
