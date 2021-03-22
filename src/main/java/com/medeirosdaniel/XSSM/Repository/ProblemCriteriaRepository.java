package com.medeirosdaniel.XSSM.Repository;

import com.medeirosdaniel.XSSM.Entity.ProblemCriteriasEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProblemCriteriaRepository extends MongoRepository<ProblemCriteriasEntity, String> {


    public ProblemCriteriasEntity findByEmail(String email);
}
