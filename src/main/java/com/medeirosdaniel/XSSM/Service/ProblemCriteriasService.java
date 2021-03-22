package com.medeirosdaniel.XSSM.Service;

import com.medeirosdaniel.XSSM.Entity.CriteriaEntity;
import com.medeirosdaniel.XSSM.Entity.ProblemCriteriasEntity;
import com.medeirosdaniel.XSSM.Repository.ProblemCriteriaRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProblemCriteriasService {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    private ProblemCriteriaRepository problemCriteriaRepository;

    public ProblemCriteriasEntity findByEmail(String email) {
        ProblemCriteriasEntity getInfo = problemCriteriaRepository.findByEmail(email);
        if (problemCriteriaRepository.findByEmail(email) != null) {
            return problemCriteriaRepository.findByEmail(email);
        }
        return new ProblemCriteriasEntity();
    }

    public ProblemCriteriasEntity createProblemCriteria(String email, CriteriaEntity criteria) {
        ProblemCriteriasEntity getInfo = new ProblemCriteriasEntity();
        List<CriteriaEntity> getlist = new ArrayList<>();
        CriteriaEntity cr = new CriteriaEntity();

        if (problemCriteriaRepository.findByEmail(email) != null) {
            getInfo = problemCriteriaRepository.findByEmail(email);
            getlist = getInfo.getCriteria();
            getlist.add(criteria);
            getInfo.setCriteria(getlist);
        } else {
            getInfo.setEmail(email);
            getlist.add(criteria);
            getInfo.setCriteria(getlist);
        }
        return problemCriteriaRepository.save(getInfo);
        }
    }
