package com.medeirosdaniel.XSSM.Controller;

import com.medeirosdaniel.XSSM.DTO.OpennedTicketDTO;
import com.medeirosdaniel.XSSM.DTO.TicketDTO;
import com.medeirosdaniel.XSSM.Entity.TicketEntity;
import com.medeirosdaniel.XSSM.Service.TicketService;
import com.medeirosdaniel.XSSM.SystemExceptions.EmailNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping(value = "api/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping
    public OpennedTicketDTO createOrUpdate(@RequestBody TicketDTO ticketDTO, @RequestBody String email) throws ParseException, MessagingException {
        return ticketService.createOrUpdateTicket(ticketDTO,email);
    }

    @GetMapping(value = "/all")
    public List<TicketEntity> findAllTickets(){
        return ticketService.listTicket();
    }
    @GetMapping(value = "/email/{email}")
    public List<TicketEntity> findTicketByEmail(@PathVariable("email") String email){
        return ticketService.findTicketByEmail(email);
    }
}
