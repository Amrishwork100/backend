package com.itech.login.app.repository;

import com.itech.login.app.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRegistrationRepository extends JpaRepository<UserData,String> {

    UserData findByEmail(String email);
}
