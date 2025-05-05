import { useState } from "react";
import TaskList from "./components/TaskList";
import TaskInput from "./components/TaskInput";
import "./style03.css";

export default function App03() {
    const [tasks, setTasks] = useState(["Học React", "Luyện tập component"]);

    const addTask = (task) => {
        setTasks([...tasks, task]);
    };

    return (
        <div className="app-container">
            <h1>📝 To-Do List</h1>
            <TaskInput addTask={addTask} />
            <TaskList tasks={tasks} />
        </div>
    );
}
