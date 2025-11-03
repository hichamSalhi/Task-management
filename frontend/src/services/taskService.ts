import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080';

export interface Task {
  id: number;
  title: string;
  description: string;
}

export const getTasks = async (): Promise<Task[]> => {
  const response = await axios.get(`${API_BASE_URL}/tasks`);
  return response.data;
};