package io.nology.to_dos.category;

import io.nology.to_dos.common.exceptions.NotFoundException;

import java.util.List;
import java.util.Optional;

import org.hibernate.validator.cfg.defs.pl.REGONDef;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/categories")
public class CategoryController {

    //boilerplate code
    private CategoryService categoryService;

    CategoryController(CategoryService categoryService) {

        this.categoryService = categoryService;
    }

    @PostMapping()
    public ResponseEntity<Category> postCategory(@RequestBody @Valid CreateCategoryDTO data){
        
        Category newCategory = this.categoryService.createCategory(data);
        return new ResponseEntity<Category>(newCategory, HttpStatus.CREATED);
        

    }

    @GetMapping()
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = this.categoryService.getAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);


        
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<Category> updateById(@PathVariable Long id, @Valid @RequestBody UpdateCategoryDTO data) throws NotFoundException{

            Optional<Category> result = this.categoryService.updateById(id, data);

            if(result.isEmpty()){
                throw new NotFoundException("Could not update book with ID " + id + " it does not exist");
            } 
       
            return new ResponseEntity<Category>(result.get(), HttpStatus.OK);
        }

        @GetMapping("/{id}")
        //Find the ID otherwise throw an exception but I had to tell my app that this method could throw an exception 
        //why am i throwing the exception in Controller? Why not in Service?
        // Rule of Thumb -Try to do the error handling in the controller layer rather than in the service 
        // because controller that deals with response entity
        // the service is fine to return an empty optional
        

        public ResponseEntity<Category> getCategoryById(@PathVariable Long id) throws NotFoundException {
            Optional<Category> category = this.categoryService.getCategoryById(id);


            // You should never call .get() directly on an Optional without first checking if the value is present.
            //  The get() method throws a NoSuchElementException when the Optional is empty. 
            //  Instead, you should check if the value is present using .isPresent() or, preferably, use the ifPresent or orElseThrow methods.
            if(category.isEmpty()){

                throw new NotFoundException("Could not find Category with ID " + id);

            }
            Category foundCategory = category.get();


            return new ResponseEntity<>(foundCategory, HttpStatus.OK);
    
    
            
        }
        


  }
