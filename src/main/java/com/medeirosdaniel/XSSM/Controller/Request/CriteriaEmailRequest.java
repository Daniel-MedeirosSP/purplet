package com.medeirosdaniel.XSSM.Controller.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CriteriaEmailRequest {

    private String getEmail;

    private String description;

    private String sla;
}
