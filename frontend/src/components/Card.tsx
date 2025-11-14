import React, {useEffect , useState} from "react";
import '../css/card.css'
import { getTasks, Task } from "../services/taskService";

const Card = () => {
    const [tasks, setTasks] = useState<Task[]>([]);

    useEffect(() => {
        const fetchTasks = async () => {
            try {
                const data = await getTasks();
                setTasks(data);
            } catch (error) {
                console.log("error catching the data ", error)
            }
        };

        fetchTasks();
    }, []);

    return (
        <>
            {
                (tasks).map((task : Task) => (
                    <div className="card" key={task.id}>
                    <h2>Task Title : {task.title}</h2>
                    <h3>Task status : {task.taskStatus}</h3>
                    <p>{task.description}</p>
                    </div>
                ))
            }
        </>
    );
}

export default Card;