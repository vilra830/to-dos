import { createStaticRouter } from "react-router";
import { TaskFormData, taskStatuses } from "../components/TaskForm/schema";
import CategoriesPage from "../pages/CategoriesPage";

// Category type definition
export interface Category {
  id: number;
  name: string;
  description: string;
}

// Task type definition
export interface Task {
  id: number;
  name: string;
  description: string;
  categoryId: number;
  category: Category;
  isArchived: boolean;
  taskStatus: TaskStatus;
}

// Task status enum
type TaskStatus =
  | "IN_PROGRESS"
  | "COMPLETED"
  | "DELETED"
  | "UPDATED"
  | "ARCHIVED"; // Add other possible statuses as needed

// Tasks array response type
type TasksResponse = Task[];

export const getAllCategories = async () => {
  const response = await fetch("http://localhost:8080/categories");
  if (!response.ok) {
    throw new Error("Failed to fetch"); // we can pass this error to the back end
  }

  return (await response.json()) as Category[];
};

export const getAllTasks = async () => {
  const response = await fetch("http://localhost:8080/tasks");
  if (!response.ok) {
    throw new Error("Failed to fetch"); // we can pass this error to the back end
  }

  return (await response.json()) as TasksResponse;
};

export const getTaskById = async (id: string) => {
  //its a string even though its along in Java because params is always string
  const response = await fetch("http://localhost:8080/tasks/" + id);
  if (!response.ok) {
    throw new Error("Failed to fetch"); // we can pass this error to the back end
  }

  return (await response.json()) as Task;
};

export const getTasksByCategoryId = async (id: string) => {
  //its a string even though its along in Java because params is always string
  const response = await fetch(
    `http://localhost:8080/tasks/by-category?categoryId=${id}`
  );
  if (!response.ok) {
    throw new Error("Failed to fetch tasks by category"); // we can pass this error to the back end
  }

  return (await response.json()) as Task[];
};

export const createTask = async (data: TaskFormData) => {
  let taskData = {
    name: data.name,
    description: data.description,
    taskStatus: data.taskStatus,
    isArchived: false,
    categoryId: 0,
  };

  if (data.categoryId && data.categoryId !== "0") {
    taskData.categoryId = Number(data.categoryId);
  } else if (
    data.categoryId === "0" &&
    data.newCategory &&
    data.newCategoryDescription
  ) {
    const newCategory: Category = await createCategory(
      data.newCategory,
      data.newCategoryDescription
    );
    taskData.categoryId = newCategory.id;
  }

  const response = await fetch("http://localhost:8080/tasks", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(taskData),
  });

  if (!response.ok) {
    throw new Error("Failed to Post");
  }
  return (await response.json()) as Task;
};

export const createCategory = async (
  name: string,
  description: string
): Promise<Category> => {
  const response = await fetch("http://localhost:8080/categories", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ name, description }),
  });
  if (!response.ok) {
    throw new Error("Failed to create Category");
  }

  return (await response.json()) as Category;
};

export const updateTask = async (taskId: string, data: TaskFormData) => {
  let taskData = {
    name: data.name,
    description: data.description,
    taskStatus: data.taskStatus,
    isArchived: false,
    categoryId: data.categoryId,
  };

  if (data.categoryId && data.categoryId !== "0") {
    taskData.categoryId = data.categoryId;
  } else if (
    data.categoryId === "0" &&
    data.newCategory &&
    data.newCategoryDescription
  ) {
    const newCategory: Category = await createCategory(
      data.newCategory,
      data.newCategoryDescription
    );
    taskData.categoryId = String(newCategory.id);
  }

  const response = await fetch(`http://localhost:8080/tasks/${taskId}`, {
    method: "PATCH",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(taskData),
  });

  if (!response.ok) {
    throw new Error("Failed to Post");
  }
  return (await response.json()) as Task;
};
