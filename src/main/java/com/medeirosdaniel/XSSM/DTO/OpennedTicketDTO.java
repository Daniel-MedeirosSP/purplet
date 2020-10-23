package com.medeirosdaniel.XSSM.DTO;

import com.medeirosdaniel.XSSM.Entity.TicketUserEntity;
import com.medeirosdaniel.XSSM.Enums.PriorityEnum;
import com.medeirosdaniel.XSSM.Enums.SlaEnum;
import com.medeirosdaniel.XSSM.Enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OpennedTicketDTO {

    private TicketUserEntity ticketUser;

    private String email;

    private String title;

    private String description;

    private String provider;

   private StatusEnum status;

    private PriorityEnum priority;

    private SlaEnum sla;

    private String ticketDateOpen;

    private String erroMSG;

    private String ticketNumber;

    private String cnpj;

    private String companyName;

    private String stateRegistration;

    private String businessSegment;

    private String cep;

    private String street;

    private Integer number;

    private String complement;

    private String neighborhood;

    private String city;

    private String state;

}
