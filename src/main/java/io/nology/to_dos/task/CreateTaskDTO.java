package io.nology.to_dos.task;

import io.nology.to_dos.category.Category;
import io.nology.to_dos.task.Task.TaskStatus;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateTaskDTO {

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

        private Long categoryId;

        private String newCategoryName;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Category getCategory() {
            return category;
        }

        public void setCategory(Category category) {
            this.category = category;
        }

        public Boolean getIsArchived() {
            return isArchived;
        }

        public void setIsArchived(Boolean isArchived) {
            this.isArchived = isArchived;
        }

        public TaskStatus getTaskStatus() {
            return taskStatus;
        }

        public void setTaskStatus(TaskStatus taskStatus) {
            this.taskStatus = taskStatus;
        }

        public Long getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(Long categoryId) {
            this.categoryId = categoryId;
        }

        public String getNewCategoryName() {
            return newCategoryName;
        }

        public void setNewCategoryName(String newCategoryName) {
            this.newCategoryName = newCategoryName;
        }

     

}





