package com.medeirosdaniel.XSSM.Controller;

import com.medeirosdaniel.XSSM.Entity.ProblemEntity;
import com.medeirosdaniel.XSSM.Service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/problem")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @GetMapping
    public List<ProblemEntity> findAll(){
        return problemService.findAll();
    }

    @PostMapping
    public ProblemEntity createOrUpdate(@RequestBody ProblemEntity problemEntity){
        return problemService.saveOrUpdate(problemEntity);
    }



}
