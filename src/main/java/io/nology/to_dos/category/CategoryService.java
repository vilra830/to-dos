package io.nology.to_dos.category;

import java.util.List;
import java.util.Optional;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class CategoryService {

    private CategoryRepository repo;

    CategoryService(CategoryRepository rep ){
    this.repo = repo;
    }

    public Category createCategory(CreateCategoryDTO data) {

        Category newCategory = new Category();
        newCategory.setName(data.getName().trim());
        newCategory.setDescription(data.getDescription().trim());

        return this.repo.save(newCategory);

    }

    public List<Category> getAll() {

        return this.repo.findAll();

    }

    public Optional<Category> getById(Long id){

        return this.repo.findById(id);
    }



    public Optional<Category> updateById(Long id, UpdateCategoryDTO data) {


        Optional<Category> result = this.getById(id);

        if(result.isEmpty()){

            return result;
        }

        Category foundCategory = result.get();

        if(data.getName() != null ){

            foundCategory.setName(data.getName().trim());
        }

        if(data.getDescription() != null){

            foundCategory.setDescription(data.getDescription().trim());
        }
        

        this.repo.save(foundCategory);

        return Optional.of(foundCategory);



    }




}
