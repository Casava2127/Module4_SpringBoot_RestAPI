export default function TaskItem({ task }) {
    return (
        <li className="task-item">
            <span>✅ {task}</span>
        </li>
    );
}
