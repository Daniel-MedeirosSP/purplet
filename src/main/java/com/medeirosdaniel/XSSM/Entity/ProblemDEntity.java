package com.medeirosdaniel.XSSM.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "problemd")
public class ProblemDEntity implements Serializable {

    private static final long serialVersionUID = -3804783336637673247L;

    @Id
    private String _id;

    private String category;

    private CriteriaEntity criteria;
}
