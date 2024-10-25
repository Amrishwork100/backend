package com.itech.springsecurity.section4.repository;

import com.itech.springsecurity.section4.model.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends CrudRepository<Contact, String> {


}
