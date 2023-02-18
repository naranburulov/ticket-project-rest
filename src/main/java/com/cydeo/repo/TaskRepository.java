package com.cydeo.repo;

import com.cydeo.entity.Project;
import com.cydeo.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    //jpql
    @Query("SELECT COUNT(t) FROM Task t WHERE t.project.projectCode = ?1 AND t.taskStatus <> 'COMPLETE'")
    int totalNonCompletedTasks(String projectCode);

    //native
    @Query(value = "SELECT COUNT(*) FROM tasks t JOIN projects p on t.project_id=p.id +" +
            "WHERE p.project_code=?1 AND t.task_status='COMPLETE'", nativeQuery = true)
    int totalCompletedTasks(String projectCode);

    List<Task> findAllByProject(Project project);
}
