package io.nology.to_dos.task;

import io.nology.to_dos.category.Category;
import io.nology.to_dos.task.Task.TaskStatus;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateTaskDTO {

        @NotNull
        @Min(1)
        private Long categoryId;

        @NotBlank
        private String name;
      
        @NotBlank
        private String description;
       
        @ManyToOne
        @JoinColumn(name = "category_id", nullable = false)
        private Category category;
       
        @NotNull
        private Boolean isArchived = false;
    
        @NotNull
        private TaskStatus taskStatus;



        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public Category getCategory() {
            return category;
        }

        public Boolean getIsArchived() {
            return isArchived;
        }

        public String getNewCategoryName() {
            return newCategoryName;
        }

        public String getNewCategoryDescription() {
            return newCategoryDescription;
        }

        public TaskStatus getTaskStatus() {
            return taskStatus;
        }

        public Long getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(Long categoryId) {
            this.categoryId = categoryId;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setCategory(Category category) {
            this.category = category;
        }

        public void setIsArchived(Boolean isArchived) {
            this.isArchived = isArchived;
        }

        public void setTaskStatus(TaskStatus taskStatus) {
            this.taskStatus = taskStatus;
        }

           // These two fields are only needed if categoryId is 0
    private String newCategoryName;
    private String newCategoryDescription;



    public void setNewCategoryName(String newCategoryName) {
        this.newCategoryName = newCategoryName;
    }

    public void setNewCategoryDescription(String newCategoryDescription) {
        this.newCategoryDescription = newCategoryDescription;
    }

        


        

}





