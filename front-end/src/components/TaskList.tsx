import { Link, Navigate } from "react-router";
import { Task } from "../services/tasks-services";
import TaskForm from "./TaskForm/TaskForm";

interface TaskListProps {
  //there is relationship between backend and frontend - our javacode is describe by our typescript code

  tasks: Task[];
}

export default function TaskList({ tasks }: TaskListProps) {
  if (tasks.length === 0) {
    return null;
  }

  return (
    <>
      <table>
        <thead>
          <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Category</th>
            <th>Task Status</th>
            <th>Archived Status</th>
          </tr>
        </thead>
        <tbody>
          {tasks.map((task) => (
            <tr key={task.id}>
              <td>
                <Link to={`/tasks/${task.id}`}>{task.name}</Link>
              </td>
              <td>{task.description}</td>
              <td>{task.category.name}</td>
              <td>{task.taskStatus}</td>
              {/* <td>{task.isArchived}</td> */}
            </tr>
          ))}
        </tbody>
      </table>
      {/* Create Button to Navigate to Task Form */}
      {/* className={styles.createTaskButtonContainer} */}
      {/* className={styles.createTaskButton} */}
      <div>
        <Link to="/tasks/new">Create New Task</Link>
      </div>
    </>
  );
}
