import React from 'react';
import ReactDOM from 'react-dom/client'
import App from './App';
import Chat from './components/Chat';

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <App />
    <Chat />
  </React.StrictMode>
);
