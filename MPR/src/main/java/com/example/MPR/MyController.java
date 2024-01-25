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
    public ResponseEntity<List<Dog>> getAllDogs(){
        List<Dog> dogs=service.getAllDogs();
        return ResponseEntity.ok(dogs);
    }

    @GetMapping("Dog/getByName/{name}")
    public ResponseEntity<Dog> getDogByName(@PathVariable String name){
        Dog dog = service.getDogByName(name);
        return ResponseEntity.ok(dog);
    }

    @GetMapping("Dog/getById/{id}")
    public ResponseEntity<Dog> getDogById(@PathVariable Long id){
        Dog dog = service.getDogById(id);
        return ResponseEntity.ok(dog);
    }

    @PostMapping("Dog/add")
    public ResponseEntity<Dog> addDog(@RequestBody Dog dog){
        service.addDog(dog);
        return ResponseEntity.ok().build();
    }

    @PutMapping("Dog/update/{id}")
    public ResponseEntity<Dog> putDog(@PathVariable Long id,@RequestBody Dog dog){
        service.updateDog(id, dog);
        service.getDogById(id);
        return ResponseEntity.ok().build();

    }
    @DeleteMapping("Dog/delete/{id}")
    public ResponseEntity<Void> deleteDog(@PathVariable Long id){
        service.deleteDog(id);
        return ResponseEntity.ok().build();
    }
}