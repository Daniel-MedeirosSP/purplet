package com.medeirosdaniel.XSSM.Repository;

import com.medeirosdaniel.XSSM.Entity.TicketEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TicketRepository extends MongoRepository<TicketEntity, String> {

    List<TicketEntity> findTicketByEmail (String email);
}
