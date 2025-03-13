package io.nology.to_dos.category;

import jakarta.validation.constraints.NotBlank;

public class CreateCategoryDTO {

    @NotBlank
    private String name;
    @NotBlank
    private String description;

        
    public String getName() {
        return name;

    }

    public String getDescription() {
        return description;

    }

    

    @Override
    public String toString() {
        return "CreateCategoryDTO [name=" + name + ", description=" + description + "]";
    }

}
