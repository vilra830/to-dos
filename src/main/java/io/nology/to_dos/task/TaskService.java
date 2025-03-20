package io.nology.to_dos.task;

import io.nology.to_dos.category.Category;
import io.nology.to_dos.category.CategoryController;

import java.util.List;
import java.util.Map;
import java.util.Set;

import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.nology.to_dos.category.CategoryRepository;
import io.nology.to_dos.category.CategoryService;
import io.nology.to_dos.category.UpdateCategoryDTO;
import io.nology.to_dos.common.GlobalExceptionHandler;
import io.nology.to_dos.common.exceptions.DuplicateCategoryException;
import io.nology.to_dos.common.exceptions.NotFoundException;
import io.nology.to_dos.config.ModelMapperConfig;
import io.nology.to_dos.task.Task.TaskStatus;
import jakarta.validation.Valid;

@Service
public class TaskService {

    private final ModelMapperConfig modelMapperConfig;

    private final GlobalExceptionHandler globalExceptionHandler;

    private final CategoryService categoryService;

    private final CategoryController categoryController;


    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CategoryRepository  categoryRepository;
    
    @Autowired
    private ModelMapper modelMapper;

    TaskService(CategoryController categoryController, CategoryService categoryService, GlobalExceptionHandler globalExceptionHandler, ModelMapperConfig modelMapperConfig) {
        this.categoryController = categoryController;
        this.categoryService = categoryService;
        this.globalExceptionHandler = globalExceptionHandler;
        this.modelMapperConfig = modelMapperConfig;
    }

    public Task createTask(CreateTaskDTO data) throws NotFoundException, DuplicateCategoryException {
        Category category;

        
            if(data.getCategoryId() != null){
                category = categoryRepository.findById(data.getCategoryId())
                            .orElseThrow(() -> new NotFoundException("Category not found with ID " + data.getCategoryId()));
            } else if (data.getNewCategoryName() != null && !data.getNewCategoryName().isEmpty()) {
                String newCategoryName = data.getNewCategoryName().trim();
                Optional <Category> checkExistingCategory = categoryRepository.findByName(newCategoryName);
                
                if(checkExistingCategory.isPresent()){
                    throw new DuplicateCategoryException(newCategoryName + " already exists");
                }

                Category newCategory = new Category();
                newCategory.setName(newCategoryName);
                category = categoryRepository.save(newCategory);
            
            } else {
                throw new IllegalArgumentException("Either categoryId or newCategoryName must be provided.");

            }
        Task task = modelMapper.map(data, Task.class);
        task.setCategory(category);
        return taskRepository.save(task);
    }

    public boolean deleteTask(Long taskId) throws NotFoundException {
        Optional<Task> result = taskRepository.findById(taskId);
     
        if(result.isEmpty()){
           throw new NotFoundException("No Employee with such ID " + taskId);
        }
   
         taskRepository.deleteById(taskId);
        return true;
        
    }

    public List<Task> getAll() {
                return taskRepository.findAll();
    }
    

    public List<Task> getTasksByCategory(Long categoryId) throws NotFoundException {

        if(!categoryRepository.existsById(categoryId)){
            throw new NotFoundException("No category found on this ID " + categoryId);

       
        }
        
        
        List<Task> tasks = taskRepository.findByCategory_Id(categoryId);
       System.out.println("Found tasks: " + tasks.size());  // Debug log
        return tasks;

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

    Optional<Task> result = this.getTaskById(id);
        if(result.isEmpty()){

            throw new NotFoundException("No Task with such ID" + id);
        }

        Task foundTask = result.get();
        modelMapper.map(data, foundTask);
        return Optional.of(taskRepository.save(foundTask));

}






}


