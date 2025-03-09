package io.nology.to_dos.category;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    public Category createCategory(CreateCategoryDTO data) {

        // Category newCategory = new Category();
        // newCategory.setName(data.getName().trim());
        // newCategory.setDescription(data.getDescription().trim());

        Category newCategory = modelMapper.map(data, Category.class); // this line replaces the 3 lines above - it says -take this data - turn it into a CAtegory class , if some values are null - skip it otherwise trim its values


        return categoryRepository.save(newCategory);

    }

    public List<Category> getAll() {

        return categoryRepository.findAll();

    }

    public Optional<Category> getById(Long id){

        return categoryRepository.findById(id);
    }



    public Optional<Category> updateById(Long id, UpdateCategoryDTO data) {
//this.getById(id) - method in this service 

        Optional<Category> result = this.getById(id);

        if(result.isEmpty()){
            //Optional.of(null) - same 
            return result;
        }

        // there should be a category found as we have already accounted if result is empty


        Category foundCategory = result.get();

        if(data.getName() != null ){

            foundCategory.setName(data.getName().trim());
        }

        if(data.getDescription() != null){

            foundCategory.setDescription(data.getDescription().trim());
        }
        

        categoryRepository.save(foundCategory);

        return Optional.of(foundCategory);



    }

    public Optional<Category> getCategoryById(Long id) {
        // // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'getCategoryById'");

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
