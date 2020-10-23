package com.medeirosdaniel.XSSM.Service;

import com.medeirosdaniel.XSSM.Entity.TicketUserEntity;
import com.medeirosdaniel.XSSM.Repository.TicketUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketUserService {

    @Autowired
    private TicketUserRepository ticketUserRepository;

    public TicketUserEntity findByEmail(String email){
        if(email == null || email.isEmpty()){
            return new TicketUserEntity();
        }
        return ticketUserRepository.findUserByEmail(email);
    }


    public TicketUserEntity createOrUpdate(TicketUserEntity ticketUserEntity) {
        if (ticketUserEntity == null) {
            return new TicketUserEntity();
        }
//        if (ticketUserRepository.hasUserByEmail(ticketUserEntity.getEmail())) {
//            return new TicketUserEntity();
//        }
        return ticketUserRepository.save(ticketUserEntity);
    }


}
