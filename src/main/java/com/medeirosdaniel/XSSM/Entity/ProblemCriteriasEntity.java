package com.medeirosdaniel.XSSM.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "criterios")
public class ProblemCriteriasEntity {

    private String _id;

    private String email;

    private List<CriteriaEntity> criteria;

}
