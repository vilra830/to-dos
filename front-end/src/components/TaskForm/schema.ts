import * as z from "zod";

export const taskStatuses = [
  "IN_PROGRESS",
  "UPDATED",
  "ARCHIVED",
  "COMPLETED",
  "DELETED",
] as const;

export const schema = z.object({
  name: z.string().min(3),
  description: z.string().min(10),
  taskStatus: z.enum(taskStatuses),
  categoryId: z.string(),
  newCategory: z.string().min(3).optional().or(z.literal("")), // adding new category
  newCategoryDescription: z.string().min(3).optional().or(z.literal("")),
});

export type TaskFormData = z.infer<typeof schema>;
