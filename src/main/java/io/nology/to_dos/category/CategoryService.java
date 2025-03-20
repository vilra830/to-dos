package io.nology.to_dos.category;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import io.nology.to_dos.common.GlobalExceptionHandler;
import io.nology.to_dos.common.exceptions.DuplicateCategoryException;
import io.nology.to_dos.common.exceptions.NotFoundException;
import io.nology.to_dos.config.ModelMapperConfig;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;

@Service
public class CategoryService {



    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;


    public Category createCategory(CreateCategoryDTO data) {
        
        Category newCategory = modelMapper.map(data, Category.class); 
        return categoryRepository.save(newCategory);
    }

    public List<Category> getAll() {

        return categoryRepository.findAll();
    }

    public Optional<Category> getById(Long id){
        return categoryRepository.findById(id);
    }



    public Optional<Category> updateById(Long id, UpdateCategoryDTO data) throws NotFoundException, DuplicateCategoryException {
        
        Optional<Category> result = this.getById(id);

            if(result.isEmpty()){

                throw new NotFoundException("No Category with such ID " + id + " found");
        }

            Category foundCategory = result.get();
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.map(data, foundCategory);

        try {

            return Optional.of(categoryRepository.save(foundCategory));

        } catch (ConstraintViolationException e) {

            throw new DuplicateCategoryException("Category Name '" + data.getName() + "' already exists");
        }

    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public boolean deleteById(Long id) {
        // TODO Auto-generated method stub
        //deletebyId or by entity - returns void - how can we know that the entity is deleted or not - query it - look up the entity
        Optional <Category> category  = this.getById(id);
        if(category.isEmpty()) {
            return false;
        } 

        categoryRepository.deleteById(id);
        return true;
    }




}
