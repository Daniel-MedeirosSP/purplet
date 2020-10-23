package com.medeirosdaniel.XSSM.Entity;

import com.medeirosdaniel.XSSM.Enums.PriorityEnum;
import com.medeirosdaniel.XSSM.Enums.SlaEnum;
import com.medeirosdaniel.XSSM.Enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Ticket")
public class TicketEntity implements Serializable {

    @Id
    private String id;

    private UserEntity user;

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
