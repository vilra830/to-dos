package io.nology.to_dos.task;

import io.nology.to_dos.category.Category;

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
    

    public List<Task> getTasksBbyCategory(Long categoryId) throws NotFoundException {
        // you need to fetch all tasks from DB using taskrepo.findbyCategory(category id)
        // Optional<Category> result = categoryRepository.findById(categoryId);

        // if (result.isEmpty()){
        //     throw new NotFoundException("No category found on this ID " + categoryId);
        // } 

        // // Category category = result.get();

        if(!categoryRepository.existsById(categoryId)){
            throw new NotFoundException("No category found on this ID " + categoryId);

       
        }
        
        
        List<Task> tasks = taskRepository.findByCategory_Id(categoryId);
       System.out.println("Found tasks: " + tasks.size());  // Debug log
        return tasks;

    }

//     public List<Task> getTasksBbyCategory(Long categoryId) throws NotFoundException {
//     if (!categoryRepository.existsById(categoryId)) {
//         throw new NotFoundException("No category found on this ID " + categoryId);
//     }
    
//     List<Task> tasks = taskRepository.findTasksByCategoryIdNative(categoryId);
    
//     // Pre-load all categories at once to avoid N+1 queries
//     Set<Long> categoryIds = tasks.stream()
//         .map(task -> task.getCategoryId())
//         .collect(Collectors.toSet());
    
//     if (!categoryIds.isEmpty()) {
//         List<Category> categories = categoryRepository.findAllById(categoryIds);
//         Map<Long, Category> categoryMap = categories.stream()
//             .collect(Collectors.toMap(Category::getId, c -> c));
            
//         // Manually set the category for each task
//         tasks.forEach(task -> {
//             Long catId = task.getCategoryId();
//             if (categoryMap.containsKey(catId)) {
//                 task.setCategory(categoryMap.get(catId));
//             }
//         });
//     }
    
//     return tasks;
// }


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
        
        
         // Update the category if the categoryId is provided
    if (data.getCategoryId() != null) {
        if (data.getCategoryId() == 0 && data.getNewCategoryName() != null && data.getNewCategoryDescription() != null) {
            // Create a new category and set it to the task if categoryId is 0 (new category)
            Category newCategory = new Category();
            newCategory.setName(data.getNewCategoryName());
            newCategory.setDescription(data.getNewCategoryDescription());
            categoryRepository.save(newCategory);
            foundTask.setCategory(newCategory); // Assign new category to task
        } else if (data.getCategoryId() != 0) {
            // Otherwise, find the existing category by ID and update the task category
            Category existingCategory = categoryRepository.findById(data.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category not found"));
            foundTask.setCategory(existingCategory);
        }
    }
        taskRepository.save(foundTask);

        return Optional.of(foundTask);



    }


}


