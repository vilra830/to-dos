package io.nology.to_dos.task;

import io.nology.to_dos.category.Category;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.nology.to_dos.category.CategoryRepository;
import io.nology.to_dos.category.CategoryService;
import io.nology.to_dos.common.exceptions.NotFoundException;
import io.nology.to_dos.task.Task.TaskStatus;
import jakarta.validation.Valid;

@Service
public class TaskService {


    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CategoryRepository  categoryRepository;

    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private ModelMapper modelMapper;

    public Task createTask(CreateTaskDTO data) throws NotFoundException {
        
        Category category = categoryRepository.findById(data.getCategoryId()).orElseThrow( () -> new NotFoundException("Category not found"));

        Task newTask = new Task();

        newTask.setCategory(category);
        newTask.setIsArchived(data.getIsArchived());
        newTask.setName(data.getName());
        newTask.setDescription(data.getDescription());
        newTask.setTaskStatus(data.getTaskStatus());

        // Task newTask = modelMapper.map(data, Task.class);

        return taskRepository.save(newTask);


    }


    public List<Task> getAll() {
                return taskRepository.findAll();
    }
    

    public List<Task> getTasksBbyCategory(Long id) throws Exception {
        // you need to fetch all tasks from DB using taskrepo.findbyCategory(category id)
        Optional<Category> result = categoryRepository.findById(id);

        if (result.isEmpty()){
            throw new Exception("No category found on this ID " + id);
        } 

        Category category = result.get();
        return taskRepository.findByCategory(category);
    }


    public List<Task> getTasksByStatus(TaskStatus taskStatus) {

        return taskRepository.findByTaskStatus(taskStatus);
    }

}


