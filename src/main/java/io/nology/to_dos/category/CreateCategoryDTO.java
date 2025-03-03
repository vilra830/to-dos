package io.nology.to_dos.category;


public class CreateCategoryDTO {

    private String name;
    private String description;

        
    public String getName() {
        return name;

    }

    public String getDescription() {
        return description;

    }

    @Override
    public String toString() {
        return "CreateCategoryDTO [name=" + name + " - "+ description + "]";
    }

}
