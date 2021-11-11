package com.dammak.project401.Repo;



import com.dammak.project401.models.NumberDonate;
import org.springframework.data.repository.CrudRepository;



public interface NumberRepo extends CrudRepository<NumberDonate,Long>{
    NumberDonate findByUsername(String username);
    }

