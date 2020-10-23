package com.medeirosdaniel.XSSM.Repository;

import com.medeirosdaniel.XSSM.Entity.BusinessEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BusinessRepository extends MongoRepository<BusinessEntity, String>{

    BusinessEntity findAddressByCnpj(String cnpj);
}
