import { useEffect, useState } from "react";
import { useParams } from "react-router";
import { getTaskById, Task, updateTask } from "../services/tasks-services";
import UpdateTaskForm from "../components/TaskForm/UpdateTaskForm";
import { TaskFormData } from "../components/TaskForm/schema";
import styles from "./TaskPage.module.scss";

export default function TaskPage() {
  const { id = "x" } = useParams();
  const [task, setTask] = useState<Task | null>(null); //null is not a parameter in Typescript - good that in TS we have what we call union types book or null
  const [isUpdating, setIsUpdating] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const [success, setSuccess] = useState<string | null>(null);

  useEffect(() => {
    getTaskById(id)
      .then((task) => setTask(task))
      .catch((e) => console.log(e));
  }, [id]);

  if (!task) {
    return null;
  }

  async function handleUpdateTask(data: TaskFormData): Promise<void> {
    try {
      setIsUpdating(true);
      setError(null);
      setSuccess(null);
      console.log("DATA BEFORE ADDED" + data.categoryId);

      const updatedTask = await updateTask(id, data);
      console.log(updatedTask);

      setTask(updatedTask);
      setSuccess("Task updated Successfully!");
    } catch (err) {
      console.error("Error updating task:", err);
      setError("Failed to update task. Please try again.");
    } finally {
      setIsUpdating(false);
    }
  }

  if (!task) {
    return <div>Loading task...</div>;
  }

  return (
    <div className={styles.taskCard}>
      <div className={styles.taskHeader}>
        <h2 className={styles.taskTitle}>Task Information</h2>
      </div>

      <div>
        <h3> {task.name} </h3>
        <h4> {task.description}</h4>
        <h5>{task.category.name}</h5>
        <h6>{task.taskStatus}</h6>
        {error && <div style={{ color: "red" }}>{error}</div>}
        {success && <div style={{ color: "green" }}>{success}</div>}
        {isUpdating && <div>Updating task...</div>}
      </div>
      <div>
        <UpdateTaskForm task={task} onSubmit={handleUpdateTask} />
      </div>
    </div>
  );
}
