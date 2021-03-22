package com.medeirosdaniel.XSSM.Repository;

import com.medeirosdaniel.XSSM.Entity.ProblemEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProblemRepository extends MongoRepository<ProblemEntity, String> {

    List<ProblemEntity> findByprofile(String profile);

    List<ProblemEntity> findByActive(Boolean active);

    ProblemEntity findByProblem(String problem);



}
