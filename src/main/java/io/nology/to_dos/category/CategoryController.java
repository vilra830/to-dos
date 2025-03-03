package io.nology.to_dos.category;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    //boilerplate code
    private CategoryService categoryService;

    CategoryController(CategoryService categoryService) {

        this.categoryService = categoryService;
    }

    @PostMapping()
    public String postCategory(@RequestBody @Valid CreateCategoryDTO data){
        
            System.out.println(data);
        return "Posted";
    }


}
