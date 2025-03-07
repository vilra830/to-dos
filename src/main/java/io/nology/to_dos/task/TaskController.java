package io.nology.to_dos.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import io.nology.to_dos.task.Task.TaskStatus;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/tasks")
public class TaskController {


    @Autowired
    private TaskService taskService;


    @PostMapping()
    public ResponseEntity<Task> createTask(@RequestBody @Valid CreateTaskDTO data) {
        //TODO: process POST request
        Task task = taskService.createTask(data);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
              

    }
    
        @GetMapping()
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = this.taskService.getAll();
        return new ResponseEntity<>(tasks, HttpStatus.OK);

      
    }

    @GetMapping("/{categoryId}")
    public List<Task> getTasksByCategoryId(@PathVariable Long categoryId) throws Exception {
        return taskService.getTasksBbyCategory(categoryId);
    }
    
    @GetMapping("/{taskStatus}")
    public List<Task> getTasksByStatus(@PathVariable TaskStatus taskStatus) {
        return taskService.getTasksByStatus(taskStatus);
    }
    
    
}
