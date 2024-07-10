package com.dj.stalwart.stalwart.service;

import com.dj.stalwart.stalwart.entity.Login;
import com.dj.stalwart.stalwart.repo.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    /**
     * Saves a Login entity to the database.
     *
     * @param login the Login entity to be saved
     */
    public void saveLogin(Login login) {
        loginRepository.save(login);
    }

    /**
     * Saves a list of Login entities to the database.
     *
     * @param logins the list of Login entities to be saved
     */
    public void saveAll(List<Login> logins) {
        loginRepository.saveAll(logins);
    }

    // get login by id
    public Login getLoginById(int id) {
        return loginRepository.findById(id).get();
    }

    // return login by contactNo
    public Login getLoginByContactNo(long contactNo) {
        return loginRepository.findByContactNo(contactNo);
    }

    public int updateLoginStatus(long loginId, String status, long detailId, String detailType) {
        return loginRepository.updateLoginStatusAndDetailsById(loginId, status, detailId, detailType);
    }
}
