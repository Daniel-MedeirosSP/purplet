package com.medeirosdaniel.XSSM.Service;

import com.medeirosdaniel.XSSM.Entity.ProblemCriteriasEntity;
import com.medeirosdaniel.XSSM.Repository.ProblemCriteriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceInitService {

    @Autowired
    private ProblemCriteriaRepository problemCriteriaRepository;

    public ProblemCriteriasEntity config(ProblemCriteriasEntity problemCriteriasEntity){
         return problemCriteriaRepository.save(problemCriteriasEntity);
    }
}
