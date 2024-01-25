package com.example.MPR;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface DogRepository
        extends CrudRepository<Dog,Long>{
    public Dog findByName(String name);





}
