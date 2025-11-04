import axios from 'axios';

const API_BASE_URL = 'https://task-management-pbp3.onrender.com';

export interface Task {
  id: number;
  title: string;
  description: string;
}

export const getTasks = async (): Promise<Task[]> => {
  const response = await axios.get(`${API_BASE_URL}/tasks`);
  return response.data;
};