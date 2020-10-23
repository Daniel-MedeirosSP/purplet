package com.medeirosdaniel.XSSM.Repository;

import com.medeirosdaniel.XSSM.Entity.TicketUserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TicketUserRepository extends MongoRepository<TicketUserEntity, String> {
    TicketUserEntity findUserByEmail (String email);

//    Boolean hasUserByEmail(String email);
}
