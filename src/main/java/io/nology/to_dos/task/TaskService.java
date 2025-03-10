package io.nology.to_dos.task;

import io.nology.to_dos.category.Category;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.nology.to_dos.category.CategoryRepository;
import io.nology.to_dos.category.CategoryService;
import io.nology.to_dos.category.UpdateCategoryDTO;
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
        Category category;

        if(data.getCategoryId() != 0 ) {
            category = categoryRepository.findById(data.getCategoryId()).orElseThrow( () -> new NotFoundException("Category not found"));


            

        } else {

            if(data.getNewCategoryName() == null || data.getNewCategoryDescription() == null){
                throw new IllegalArgumentException("New category name and description must be provided");
            }

            Category newCategory = new Category();
            newCategory.setName(data.getNewCategoryName());
            newCategory.setDescription(data.getNewCategoryDescription());
            category = categoryRepository.save(newCategory);

        
        }

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
    

    public List<Task> getTasksBbyCategory(Long id) throws NotFoundException {
        // you need to fetch all tasks from DB using taskrepo.findbyCategory(category id)
        Optional<Category> result = categoryRepository.findById(id);

        if (result.isEmpty()){
            throw new NotFoundException("No category found on this ID " + id);
        } 

        Category category = result.get();
        return taskRepository.findByCategory(category);
    }


    public List<Task> getTasksByStatus(TaskStatus taskStatus) {

        return taskRepository.findByTaskStatus(taskStatus);
    }


    public Optional<Task> getTaskById(Long taskId) throws NotFoundException {

        Optional<Task> result = taskRepository.findById(taskId);
        if(result.isEmpty()){

            throw new NotFoundException("No Task with such ID" + taskId);
        }

        return result;

    }






public Optional<Task> updateById(Long id, UpdateTaskDTO data) throws NotFoundException {
//this.getById(id) - method in this service 



        Optional<Task> result = this.getTaskById(id);
        if(result.isEmpty()){

            throw new NotFoundException("No Task with such ID" + id);
        }



        // there should be a category found as we have already accounted if result is empty


        Task foundTask = result.get();

        if(data.getName() != null ){

            foundTask.setName(data.getName().trim());
        }

        if(data.getDescription() != null){

            foundTask.setDescription(data.getDescription().trim());
        }
        if (data.getTaskStatus() != null) {
            foundTask.setTaskStatus(data.getTaskStatus());
        }
        
        if(data.getDescription() != null){

            foundTask.setDescription(data.getDescription().trim());
        }
        
        
        

        taskRepository.save(foundTask);

        return Optional.of(foundTask);



    }


}


