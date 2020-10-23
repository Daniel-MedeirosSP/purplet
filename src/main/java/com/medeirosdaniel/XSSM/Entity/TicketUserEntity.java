package com.medeirosdaniel.XSSM.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="TicketUser")
public class TicketUserEntity {

    @Id
    private String id;

    @NotBlank(message = "É Requerido Nome Completo!")
    @NotNull
    private String fullname;

    @NotBlank(message = "É Requerido E-Mail")
    @NotNull
    @Email
    private String email;

    @NotBlank(message = "É Requerido CNPJ!")
    @NotNull
    @Size(min=14,max=14)
    private String cnpj;

    @NotBlank(message = "É Requerido Telefone ou Celular!")
    @NotNull
    @Size(min=12,max=13)
    private String celular;

    private Boolean isWhatsApp;

    private Boolean isTelegram;


}
