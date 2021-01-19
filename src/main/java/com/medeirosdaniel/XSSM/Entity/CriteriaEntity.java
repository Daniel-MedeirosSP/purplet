package com.medeirosdaniel.XSSM.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CriteriaEntity {


    private String problem;

    private String profile;

    private Boolean active;

    private String sla;

    private String priority;
}