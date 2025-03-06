package io.nology.to_dos.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


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
    
}
