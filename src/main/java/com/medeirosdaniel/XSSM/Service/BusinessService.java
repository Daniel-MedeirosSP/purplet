package com.medeirosdaniel.XSSM.Service;

import com.medeirosdaniel.XSSM.Entity.BusinessEntity;
import com.medeirosdaniel.XSSM.Repository.BusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessService {
    @Autowired
    private BusinessRepository businessRepository;

    public BusinessEntity findAddressByCnpj(String cnpj){
        if(cnpj == null){
            return new BusinessEntity();
        }
        return businessRepository.findAddressByCnpj(cnpj);
    }
}
