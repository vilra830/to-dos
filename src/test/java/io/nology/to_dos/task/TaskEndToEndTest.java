package io.nology.to_dos.task;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import io.nology.to_dos.category.Category;
import io.nology.to_dos.category.CategoryRepository;
import io.nology.to_dos.task.Task.TaskStatus;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

// tell springboot this test

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class TaskEndToEndTest {
    @LocalServerPort //we need to tell springboot about that
    private int port;

    private ArrayList<Task> tasks = new ArrayList<>();

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach //before every test Im going to create a coubple of task
    public void setUp() {
        RestAssured.port = port;
        taskRepository.deleteAll();
        tasks.clear();
    

//setup data for tests
//be careful to make sure you're saving valid data

//create category so I can post task with Category
Category operationsCategory = categoryRepository.save(new Category("Operations", "Tasks associated daily operational flows of the business"));
Category financeCategory = categoryRepository.save(new Category("Finance", "Tasks associated with financial management, payrolls, and taxation"));


Task task1 = new Task();
task1.setName("Operations Staff Meeting");

task1.setDescription("Conduct a meeting to review daily operations and address any challenges");
task1.setCategory(operationsCategory);
task1.setTaskStatus(TaskStatus.IN_PROGRESS);
task1.setIsArchived(false);
taskRepository.save(task1);
tasks.add(task1);



Task task2 = new Task();
task2.setName("Monthly Budget Review");
task2.setDescription("Review and finalize the monthly budget report for March");
task2.setCategory(financeCategory);
task2.setTaskStatus(TaskStatus.IN_PROGRESS);
task2.setIsArchived(false);
taskRepository.save(task2);
tasks.add(task2);


}

@Test 
public void getAllTasks_TasksInDB_ReturnsSucess(){
    //arrange. done in beforeEach
    given().
        when()
            .get("/tasks")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("$", hasSize(2))
            .body("name", hasItems("Operations Staff Meeting","Monthly Budget Review"));

}



}
