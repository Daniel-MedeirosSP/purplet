package com.medeirosdaniel.XSSM.DTO;

import com.medeirosdaniel.XSSM.Entity.TicketUserEntity;
import com.medeirosdaniel.XSSM.Enums.PriorityEnum;
import com.medeirosdaniel.XSSM.Enums.SlaEnum;
import com.medeirosdaniel.XSSM.Enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TicketDTO {

    private String id;

    private TicketUserEntity ticketUser;

    private String email;

    private String title;

    private String description;

    private String provider;

    private String customerServiceAddress;

    private StatusEnum status;

    private PriorityEnum priority;

    private SlaEnum sla;

    private Date ticketDateOpen;

    private String erroMSG;

    private String ticketNumber;

}
