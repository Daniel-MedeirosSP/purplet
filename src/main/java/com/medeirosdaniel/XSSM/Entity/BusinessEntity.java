package com.medeirosdaniel.XSSM.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "business")
public class BusinessEntity {
    @Id
    private String id;

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
