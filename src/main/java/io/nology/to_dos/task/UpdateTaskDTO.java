package io.nology.to_dos.task;

import io.nology.to_dos.category.Category;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class UpdateTaskDTO {

     @Pattern(regexp = ".*\\S.*", message = "Name cannot be empty")
    private String name;
    @Pattern(regexp = ".*\\S.*", message = "Description cannot be empty")    
    private String description;
            @NotNull
        @Min(1)
        private Long categoryId;

    private Boolean isArchived = false;

  
    public Boolean getIsArchived() {
        return isArchived;
    }
    public void setIsArchived(Boolean isArchived) {
        this.isArchived = isArchived;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }

    private Task.TaskStatus taskStatus;  // Assuming TaskStatus is an enum and can be updated

    private Category category;

    // Getters and setters for all fields
 
    public void setName(String name) {
        this.name = name;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public Task.TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Task.TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }

    private String newCategoryName;
    private String newCategoryDescription;


    public String getNewCategoryName() {
        return newCategoryName;
    }
    public void setNewCategoryName(String newCategoryName) {
        this.newCategoryName = newCategoryName;
    }
    public String getNewCategoryDescription() {
        return newCategoryDescription;
    }
    public void setNewCategoryDescription(String newCategoryDescription) {
        this.newCategoryDescription = newCategoryDescription;
    }
    public Long getCategoryId() {
        return categoryId;
    }

}



    



