package com.example.MPR;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MyController {

    private final MyRestService service;

    @Autowired
    public MyController(MyRestService service) {
        this.service = service;
    }

    @GetMapping("Dog/getAll")
    public List<Dog> getAllDogs(){
        List<Dog> dogs=service.getAllDogs();
        return dogs;
    }

    @GetMapping("Dog/getByName/{name}")
    public Dog getDogByName(@PathVariable String name){
        Dog dog = service.getDogByName(name);
        return dog;
    }

    @GetMapping("Dog/getById/{id}")
    public Dog getDogById(@PathVariable Long id){
        Dog dog = service.getDogById(id);
        return dog;
    }

    @PostMapping("Dog/add")
    public Dog addDog(@RequestBody Dog dog){
        return service.addDog(dog);

    }

    @PutMapping("Dog/update/{id}")
    public Dog putDog(@PathVariable Long id,@RequestBody Dog dog){
        service.updateDog(id, dog);
        return service.getDogById(id);


    }
    @DeleteMapping("Dog/delete/{id}")
    public void deleteDog(@PathVariable Long id){
        service.deleteDog(id);

    }
}