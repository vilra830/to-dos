package io.nology.to_dos.category;

import jakarta.validation.constraints.NotBlank;

public class CreateCategoryDTO {

    @NotBlank
    private String name;
        
    public String getName() {
        return name;

    }

}
