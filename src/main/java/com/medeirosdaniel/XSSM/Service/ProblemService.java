package com.medeirosdaniel.XSSM.Service;

import com.medeirosdaniel.XSSM.Entity.ProblemEntity;
import com.medeirosdaniel.XSSM.Repository.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProblemService {

    @Autowired
    private ProblemRepository problemRepository;

    public List<ProblemEntity> findAll(){
        return problemRepository.findAll();
    }

    public Optional<ProblemEntity> findById(String id){
        return problemRepository.findById(id);

    }
    public ProblemEntity saveOrUpdate(ProblemEntity problemEntity){
        return problemRepository.save(problemEntity);
    }

    public ProblemEntity findByProblem(String problem){
        return problemRepository.findByProblem(problem);
    }
}
