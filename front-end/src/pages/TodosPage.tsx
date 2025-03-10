import { useEffect, useState } from "react";
import { getAllTasks, Task } from "../services/tasks-services";
import TaskList from "../components/TaskList";

const ToDosPage = () => {
  const [tasks, setTasks] = useState<Task[]>([]);

  useEffect(() => {
    getAllTasks()
      .then((tasks) => setTasks(tasks))
      .catch((e) => console.log(e));
  }, []);

  return <TaskList tasks={tasks} />;
};

export default ToDosPage;
