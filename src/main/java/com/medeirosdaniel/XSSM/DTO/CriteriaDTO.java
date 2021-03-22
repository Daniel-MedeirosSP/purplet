package com.medeirosdaniel.XSSM.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CriteriaDTO {

    private String description;

    private String sla;

    private String erroMSG;
}
