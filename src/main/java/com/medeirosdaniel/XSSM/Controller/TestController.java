package com.medeirosdaniel.XSSM.Controller;

import com.medeirosdaniel.XSSM.Entity.ProblemCriteriasEntity;
import com.medeirosdaniel.XSSM.Service.ServiceInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @Autowired
    private ServiceInitService serviceInit;


    @PostMapping("/config-purplet")
    public ProblemCriteriasEntity initConfigPurplet(@RequestBody ProblemCriteriasEntity problemCriteriasEntity){
        return serviceInit.config(problemCriteriasEntity);

    }

}
