package com.example.aplikacja.controllers;

import com.example.aplikacja.model.Category;
import com.example.aplikacja.repositories.CategoryRepository;
import com.example.aplikacja.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private final CategoryService service;
    public CategoryController(CategoryService service){
        this.service=service;
    }
    @GetMapping("/all")
    public List<Category> getAllCategories() {
        List<Category> categories = service.getAllCategories();
        return categories;
    }

    @GetMapping("/byId/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        Category category = service.getCategoryById(id);
        return category;
    }

    @PostMapping("/add")
    public Category createCategory(@RequestBody Category category) {
        return service.createCategory(category);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category categoryDetails) {
        Category category= service.getCategoryById(id);
        if(category==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Category newCategory = service.updateCategory(id,categoryDetails);
        return new ResponseEntity<>(newCategory,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCategory(@PathVariable Long id) {
        service.deleteCategory(id);
    }
}
