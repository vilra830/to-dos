package io.nology.to_dos.task;

import io.nology.to_dos.category.Category;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.nology.to_dos.category.CategoryRepository;
import io.nology.to_dos.category.CategoryService;
import io.nology.to_dos.common.exceptions.NotFoundException;
import jakarta.validation.Valid;

@Service
public class TaskService {


    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CategoryRepository  categoryRepository;

    @Autowired
    private CategoryService categoryService;
    

    public Task createTask(CreateTaskDTO data) {
        
        Category category = categoryRepository.findById(data.getCategoryId()).orElseThrow( () -> new NotFoundException("Category not found"));

        Task newTask = new Task();

        newTask.setCategory(category);
        newTask.setIsArchived(data.getIsArchived());
        newTask.setName(data.getName());
        newTask.setDescription(data.getDescription());
        newTask.setTaskStatus(data.getTaskStatus());

        return taskRepository.save(newTask);


    }

}


