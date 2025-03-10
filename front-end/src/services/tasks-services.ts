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
  category: Category;
  isArchived: boolean;
  taskStatus: TaskStatus;
}

// Task status enum
type TaskStatus = "IN_PROGRESS" | "COMPLETED" | "PENDING" | "CANCELLED"; // Add other possible statuses as needed

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
