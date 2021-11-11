package com.dammak.project401.Repo;



import com.dammak.project401.models.AppUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<AppUser,Long> {
    AppUser findByUsername(String username);
    AppUser findByAuthority(String authority);


}