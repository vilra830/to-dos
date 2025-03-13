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

import org.springframework.web.bind.annotation.DeleteMapping;
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
                throw new NotFoundException("Could not update Category with ID " + id + " it does not exist");
            } 
       
            return new ResponseEntity<Category>(result.get(), HttpStatus.OK);
        }

        @GetMapping("/{id}")
        public ResponseEntity<Category> getCategoryById(@PathVariable Long id) throws NotFoundException {
            Optional<Category> category = this.categoryService.getCategoryById(id);


           
            if(category.isEmpty()){

                throw new NotFoundException("Could not find Category with ID " + id);

            }
            Category foundCategory = category.get();

            //if it does not match our DTO - it will return 400 Bad Request
            return new ResponseEntity<>(foundCategory, HttpStatus.OK);
    
        }
        // IWANT TO UNDERSTAND THIS
        @DeleteMapping("/{id}")
        public ResponseEntity<?> deleteById(@PathVariable Long id) throws NotFoundException{
            // a couple of choices when deleting
            //response could be the thing that you just deleted
            // respond with a 204 no content
            boolean wasDeleted = this.categoryService.deleteById(id);
            if(!wasDeleted) {

                throw new NotFoundException("Could not delete Category with id " + id + " as it does not exist");
            }

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);


        }

        


  }
