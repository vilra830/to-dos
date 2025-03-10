import { useEffect, useState } from "react";
import { useParams } from "react-router";
import { getTaskById, Task } from "../services/tasks-services";

export default function TaskPage() {
  const { id = "x" } = useParams();
  const [task, setTask] = useState<Task | null>(null); //null is not a parameter in Typescript - good that in TS we have what we call union types book or null

  useEffect(() => {
    getTaskById(id)
      .then((task) => setTask(task))
      .catch((e) => console.log(e));
  }, [id]);

  if (!task) {
    return null;
  }

  return (
    <>
      <h2> Task Inforamtion </h2>
      <h3> {task.name} </h3>
      <h4> {task.description}</h4>
      <h5>{task.category.name}</h5>
      <h6>{task.taskStatus}</h6>
      <button>Update Task</button>
    </>
  );
}
