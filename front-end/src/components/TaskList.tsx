import { Link, Navigate } from "react-router";
import { Task } from "../services/tasks-services";
import TaskForm from "./TaskForm/TaskForm";
import styles from "./TaskForm/Tasklist.module.scss";

interface TaskListProps {
  //there is relationship between backend and frontend - our javacode is describe by our typescript code

  tasks: Task[];
}

export default function TaskList({ tasks }: TaskListProps) {
  if (tasks.length === 0) {
    return null;
  }

  return (
    <div className={styles.taskListContainer}>
      <h2 className={styles.title}>Task List</h2>

      <table className={styles.taskTable}>
        <thead>
          <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Category</th>
            <th>Task Status</th>
          </tr>
        </thead>
        <tbody>
          {tasks.map((task) => (
            <tr key={task.id}>
              <td>
                <Link to={`/tasks/${task.id}`} className={styles.taskLink}>
                  {task.name}
                </Link>
              </td>
              <td>{task.description}</td>
              <td>{task.category.name}</td>
              <td>{task.taskStatus}</td>
            </tr>
          ))}
        </tbody>
      </table>
      {/* Create Button to Navigate to Task Form */}
      {/* className={styles.createTaskButtonContainer} */}
      {/* className={styles.createTaskButton} */}
      <div className={styles.createTaskButtonContainer}>
        <Link to="/tasks/new" className={styles.createTaskButton}>
          Create New Task
        </Link>
      </div>
    </div>
  );
}
