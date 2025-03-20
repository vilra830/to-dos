package io.nology.to_dos.category;

import jakarta.validation.constraints.Pattern;

public class UpdateCategoryDTO {

    
    @Pattern(regexp = ".*\\S.*", message = "Name cannot be empty")
    private String name;
   
    public String getName() {
        return name;
    }


    


}
