package com.medeirosdaniel.XSSM.Entity;

import com.medeirosdaniel.XSSM.Enums.SlaEnum;
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

 private String description;

 private String sla;
}