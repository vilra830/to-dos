package io.nology.to_dos.task;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import io.nology.to_dos.category.Category;
import io.nology.to_dos.task.Task.TaskStatus;



@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

   // @Query("SELECT t FROM Task t JOIN FETCH t.category WHERE t.category.id = :categoryId")
   List<Task> findByCategory_Id(Long categoryId);
   //     List<Task> findByCategory_Id(@Param("categoryId") Long categoryId);


//    @Query(value = "SELECT t.* FROM tasks t WHERE t.category_id = :categoryId", nativeQuery = true)
// List<Task> findTasksByCategoryIdNative(@Param("categoryId") Long categoryId);

   List<Task> findByTaskStatus(TaskStatus taskStatus);
    

}
