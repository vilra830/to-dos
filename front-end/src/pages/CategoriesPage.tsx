import { useEffect, useState } from "react";
import { Category, getAllCategories } from "../services/tasks-services";

const CategoriesPage = () => {
  const [categories, setCategories] = useState<Category[]>([]);

  useEffect(() => {
    getAllCategories()
      .then((categories) => setCategories(categories))
      .catch((e) => console.log(e));
  }, []);

  return;
  <></>;
};

export default CategoriesPage;
