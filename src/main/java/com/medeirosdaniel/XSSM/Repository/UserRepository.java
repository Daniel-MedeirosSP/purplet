package com.medeirosdaniel.XSSM.Repository;

import com.medeirosdaniel.XSSM.Entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserEntity, String> {

    UserEntity findByUsername (String username);

    UserEntity findByEmail (String email);
}
