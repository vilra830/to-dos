import { useEffect, useState } from "react";
import {
  Category,
  getAllCategories,
  getAllTasks,
  Task,
} from "../services/tasks-services";
import TaskList from "../components/TaskList";

const ToDosPage = () => {
  const [tasks, setTasks] = useState<Task[]>([]);
  const [categories, setCategories] = useState<Category[]>([]);
  const [selectedCategory, setSelectedCategory] = useState<string>("");

  useEffect(() => {
    getAllTasks()
      .then((tasks) => setTasks(tasks))
      .catch((e) => console.log(e));

    getAllCategories()
      .then((categories) => setCategories(categories))
      .catch((e) => console.log(e));
  }, []);

  const filteredTasks = selectedCategory
    ? tasks.filter((task) => task.category.id.toString() === selectedCategory)
    : tasks;

  const handleCategoryChange = (
    event: React.ChangeEvent<HTMLSelectElement>
  ) => {
    setSelectedCategory(event.target.value);
  };

  return (
    <div>
      {/* Dropdown for category selection */}
      <div>
        <label htmlFor="category">Select Category: </label>
        <select
          id="category"
          value={selectedCategory}
          onChange={handleCategoryChange}
        >
          <option value="">All Categories</option>
          {categories.map((category) => (
            <option key={category.id} value={category.id.toString()}>
              {category.name}
            </option>
          ))}
        </select>
      </div>
      <TaskList tasks={filteredTasks} />;
    </div>
  );
};

export default ToDosPage;
