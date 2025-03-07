package io.nology.to_dos.task;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import io.nology.to_dos.category.Category;
import io.nology.to_dos.task.Task.TaskStatus;



@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

   List<Task> findByCategory(Category category);

   List<Task> findByTaskStatus(TaskStatus taskStatus);
    

}
