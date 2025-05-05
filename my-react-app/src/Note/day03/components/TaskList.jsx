import TaskItem from "./TaskItem";

export default function TaskList({ tasks }) {
    return (
        <ul className="task-list">
            {tasks.map((task, index) => (
                <TaskItem key={index} task={task} />
            ))}
        </ul>
    );
}
