import { BrowserRouter, Route, Routes } from "react-router";
import TodosPage from "./pages/TodosPage";
import TaskPage from "./pages/TaskPage";

function App() {
  return (
    <BrowserRouter>
      <h1>ToDos App</h1>

      <Routes>
        <Route path="/" element={<TodosPage />}></Route>
        <Route path="/tasks/:id" element={<TaskPage />}></Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
