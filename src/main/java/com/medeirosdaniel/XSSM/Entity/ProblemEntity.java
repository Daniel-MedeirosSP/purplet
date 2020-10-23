package com.medeirosdaniel.XSSM.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "Problem")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProblemEntity implements Serializable{

    private static final long serialVersionUID = -3804783336637673247L;
    @Id
    private String id;

    private long selectId;

    //Informatica
    private String category;

    //Hardware, Software
//    private String type;

    //virus
    private String problem;

    private String profile;

    private Boolean active;

    private String sla;

    private String priority;
}
