package io.nology.to_dos.task;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import io.nology.to_dos.category.UpdateCategoryDTO;
import io.nology.to_dos.common.exceptions.NotFoundException;
import io.nology.to_dos.task.Task.TaskStatus;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskService taskService;


    @PostMapping()
    public ResponseEntity<Task> createTask(@RequestBody @Valid CreateTaskDTO data) throws NotFoundException {
        //TODO: process POST request
        Task task = taskService.createTask(data);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
              

    }
    
        @GetMapping()
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = this.taskService.getAll();
        return new ResponseEntity<>(tasks, HttpStatus.OK);

      
    }

    @GetMapping("/category/{categoryId}")
    public List<Task> getTasksByCategoryId(@PathVariable Long categoryId) throws Exception {
        return taskService.getTasksBbyCategory(categoryId);
    }

    
    
    @GetMapping("/status/{taskStatus}")
    public List<Task> getTasksByStatus(@PathVariable TaskStatus taskStatus) {
        return taskService.getTasksByStatus(taskStatus);
    }
    
    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long taskId) throws NotFoundException {
        Optional<Task> task = taskService.getTaskById(taskId);
        if(task.isEmpty()){
            throw new NotFoundException("Could not find Task with ID " + taskId);
        }

        Task foundTask = task.get();

        return new ResponseEntity<>(foundTask, HttpStatus.OK);
    }

    
    @PatchMapping("/{id}")
    public ResponseEntity<Task> updateById(@PathVariable Long id, @Valid @RequestBody UpdateTaskDTO data) throws NotFoundException{

            Optional<Task> result = taskService.updateById(id, data);

            if(result.isEmpty()){
                throw new NotFoundException("Could not update Category with ID " + id + " it does not exist");
            } 
       
            return new ResponseEntity<Task>(result.get(), HttpStatus.OK);
        }

    
}
