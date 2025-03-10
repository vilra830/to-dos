package io.nology.to_dos.task;

import jakarta.validation.constraints.Pattern;

public class UpdateTaskDTO {

     @Pattern(regexp = ".*\\S.*", message = "Name cannot be empty")
    private String name;
    @Pattern(regexp = ".*\\S.*", message = "Description cannot be empty")    
    private String description;

  
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }

    private Task.TaskStatus taskStatus;  // Assuming TaskStatus is an enum and can be updated

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
}



    



