package io.nology.to_dos.task;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.nology.to_dos.category.Category;  

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name= "tasks")


public class Task {

    public Task() {
    }

    public enum TaskStatus {
        IN_PROGRESS,
        UPDATED,
        ARCHIVED,
        COMPLETED,
        DELETED;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   
    @Column(nullable = false)
    private String name;
  
    @Column(nullable = false)
    private String description;
   
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    @JsonIgnoreProperties
    private Category category;
   
    @Column(nullable = false)
    private Boolean isArchived = false;

    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;
    
    public Category getCategory() {
        return category;
    }
    public Boolean getIsArchived() {
        return isArchived;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
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

    public Long getCategoryId() {
        return category != null ? category.getId() : null;
    }
   
    public void setId(Long id) {
        this.id = id;
    }
    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }
    public Long getId() {
        return id;
    }
    public TaskStatus getTaskStatus() {
        return taskStatus;
    }



}
