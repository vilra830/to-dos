import { useNavigate } from "react-router";
import { TaskFormData } from "../components/TaskForm/schema";
import TaskForm from "../components/TaskForm/TaskForm";
import { createTask } from "../services/tasks-services";

export default function NewTaskPage() {
  const navigate = useNavigate();
  const onSubmit = (data: TaskFormData) => {
    createTask(data)
      .then((task) => {
        console.log("task created", task);
        navigate("/");
      })
      .catch((e) => console.warn(e));
  }; //tells me what data Im working with
  return (
    <>
      <h2> Create a New Task</h2>
      <TaskForm onSubmit={onSubmit} />
    </>
  );
}
