import { useState } from "react";

export default function TaskInput({ addTask }) {
    const [task, setTask] = useState("");

    const handleSubmit = (e) => {
        e.preventDefault();
        if (task.trim() !== "") {
            addTask(task);
            setTask("");
        }
    };

    return (
        <form onSubmit={handleSubmit} className="task-input">
            <input
                type="text"
                value={task}
                onChange={(e) => setTask(e.target.value)}
                placeholder="Nhập công việc..."
            />
            <button type="submit">Thêm</button>
        </form>
    );
}
