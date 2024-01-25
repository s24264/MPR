package com.example.MPR;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class MyRestService {

   private final DogRepository dogRep;
    public MyRestService(DogRepository dogRep){
        this.dogRep=dogRep;
        this.dogRep.save(new Dog(4,"burek","czarny"));
        this.dogRep.save(new Dog(6,"placzek","szary"));
        this.dogRep.save(new Dog(7,"pimpek","rudy"));
        this.dogRep.save(new Dog(5,"kapi","bialo-czarny"));
        this.dogRep.save(new Dog(2,"lajkonik","fioletowy"));
    }

    public List<Dog> getAllDogs(){
        return (List<Dog>) dogRep.findAll();
    }
    public Dog getDogByName(String name){
        Dog dog = dogRep.findByName(name);
        if(dog == null){
            throw  new NoSuchElementException("Dog not found: " + name);
        }else {
            return dog;
        }
    }

    public Dog getDogById(Long id){
        Optional<Dog> optionalDog = dogRep.findById(id);
        if(optionalDog.isPresent()){
            Dog dog = optionalDog.get();
            return dog;
        }else{
            throw new NoSuchElementException("Dog not found id :" + id);
        }
    }

    public Dog addDog(Dog dog){
        return dogRep.save(dog);
    }

    public void updateDog(Long id, Dog dog){
        Optional<Dog> newdog = dogRep.findById(id);
        if(newdog.isPresent()){
            Dog existDog = dogRep.findById(id).orElseThrow();
            existDog.setName(dog.getName());
            existDog.setAge(dog.getAge());
            existDog.setColor(dog.getColor());
            dogRep.save(existDog);
        }else {
            throw new NoSuchElementException("Dog not found id : " + id);
        }
    }

    public void deleteDog(Long id){
        dogRep.deleteById(id);
    }



}