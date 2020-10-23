package com.medeirosdaniel.XSSM.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class PersonForm {


    @NotBlank(message = "Nome é Mandatório")
    @Size(min=2, max=30)
    private String username;

    
    @NotBlank(message = "Email é Mandatório")
    @Size(min=3, max=50)
    private String email;
}
