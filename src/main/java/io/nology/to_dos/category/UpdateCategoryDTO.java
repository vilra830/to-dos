package io.nology.to_dos.category;

import jakarta.validation.constraints.Pattern;

public class UpdateCategoryDTO {

    
    @Pattern(regexp = ".*\\S.*", message = "Name cannot be empty")
    private String name;
    @Pattern(regexp = ".*\\S.*", message = "Title cannot be empty")    
    private String description;


    
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }

    


}
