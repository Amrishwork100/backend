package com.itech.login.app.repository;

import com.itech.login.app.entity.UserRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRegistrationRepository extends JpaRepository<UserRegistration,String> {

    UserRegistration findByEmail(String email);
}
