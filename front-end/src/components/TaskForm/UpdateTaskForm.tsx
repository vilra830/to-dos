// UpdateTaskForm.tsx

import { useForm } from "react-hook-form";
import { schema, TaskFormData, taskStatuses } from "./schema";
import { capitalizedEachWord } from "./utils";
import {
  Category,
  getAllCategories,
  Task,
} from "../../services/tasks-services";
import styles from "./TaskForm.module.scss";
import { zodResolver } from "@hookform/resolvers/zod";
import { useEffect, useState } from "react";

interface UpdateTaskFormProps {
  task: Task; // Expect task to be passed in as prop for pre-filling
  onSubmit: (data: TaskFormData) => void; // Handle form submission
}

export default function UpdateTaskForm({
  task,
  onSubmit,
}: UpdateTaskFormProps) {
  const [categories, setCategories] = useState<Category[]>([]);
  const [isNewCategory, setNewCategory] = useState(false);

  const {
    register,
    handleSubmit,
    setValue,
    formState: { isSubmitSuccessful, errors },
    reset,
  } = useForm<TaskFormData>({
    resolver: zodResolver(schema),
    defaultValues: {
      name: task.name,
      description: task.description,
      taskStatus: task.taskStatus,
      categoryId: String(task.category.id),
    },
  });

  useEffect(() => {
    getAllCategories()
      .then((categories) => setCategories(categories))
      .catch((e) => console.log(e));
  }, []);

  useEffect(() => {
    reset({
      name: task.name,
      description: task.description,
      taskStatus: task.taskStatus,
      categoryId: String(task.category.id),
    });
  }, [task, reset]);

  const handleCategoryChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    const selectedValue = e.target.value;
    if (selectedValue === "0") {
      setNewCategory(true);
      setValue("newCategory", "", { shouldValidate: true });
      setValue("newCategoryDescription", "", { shouldValidate: true });
    } else {
      setNewCategory(false);
      setValue("newCategory", "", { shouldValidate: false });
      setValue("newCategoryDescription", "", { shouldValidate: false });
    }
  };

  return (
    <form
      onSubmit={handleSubmit(onSubmit)}
      className={styles["form-container"]}
    >
      <div>
        <label>Task Name</label>
        <input type="text" {...register("name")} />
        {errors.name && (
          <small style={{ color: "red" }}>{errors.name.message}</small>
        )}
      </div>
      <div>
        <label>Description</label>
        <input type="text" {...register("description")} />
        {errors.description && (
          <small style={{ color: "red" }}>{errors.description.message}</small>
        )}
      </div>
      <div>
        <label>Task Status</label>
        <select {...register("taskStatus")}>
          {taskStatuses.map((taskStatus) => (
            <option key={taskStatus} value={taskStatus}>
              {capitalizedEachWord(taskStatus)}
            </option>
          ))}
        </select>
        {errors.taskStatus && (
          <small style={{ color: "red" }}>{errors.taskStatus.message}</small>
        )}
      </div>
      <div>
        <label>Category</label>
        <select {...register("categoryId")} onChange={handleCategoryChange}>
          <option value="">Select a Category</option>
          {categories.map((category) => (
            <option key={category.id} value={category.id}>
              {category.name}
            </option>
          ))}
          <option value="0">Add New Category</option>
        </select>
        {errors.categoryId && (
          <small style={{ color: "red" }}>{errors.categoryId.message}</small>
        )}
      </div>

      {isNewCategory && (
        <>
          <div>
            <label>New Category Name</label>
            <input type="text" {...register("newCategory")} />
            {errors.newCategory && (
              <small style={{ color: "red" }}>
                {errors.newCategory.message}
              </small>
            )}
          </div>

          <div>
            <label>New Category Description</label>
            <textarea {...register("newCategoryDescription")} />
            {errors.newCategoryDescription && (
              <small style={{ color: "red" }}>
                {errors.newCategoryDescription.message}
              </small>
            )}
          </div>
        </>
      )}

      <button type="submit">Update Task</button>
    </form>
  );
}
