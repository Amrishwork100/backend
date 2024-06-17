package com.itech.login.repository;

import com.itech.login.entity.UserRegisterRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRegistrationRepository extends JpaRepository<UserRegisterRequest,String> {

    UserRegisterRequest findByEmail(String email);
}
