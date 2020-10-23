package com.medeirosdaniel.XSSM.Repository;

import com.medeirosdaniel.XSSM.Entity.PersonForm;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository2 extends MongoRepository<PersonForm,String> {
}
