import axios from "axios";
import React, { useEffect, useState } from "react";
import SockJS from "sockjs-client"
import {over} from "stompjs"

let stompClient: any = null;

export default function Chat() {
  const [message, setMessage] = useState("");
  const [messages, setMessages] = useState<{sender: string, content: string}[]>([]);
  const [sender, setSender] = useState<string>("unknown");
  const [userNameEntered, setUserNameEntered] = useState<boolean>(false);

  useEffect(() => {
    fetch("https://task-management-pbp3.onrender.com/chat/history")
    .then(res => res.json())
    .then(data => setMessages(data));

    const socket = new SockJS("https://task-management-pbp3.onrender.com/ws");
    stompClient = over(socket);
    stompClient.connect({}, () => {
      stompClient.subscribe("/topic/messages", (payload: any) => {
        const msg = JSON.parse(payload.body);
        setMessages(prev => [...prev, msg]);
      });
    });
  }, []);

  const sendMessage = () => {
    if (message.trim() !== "") {
      
      stompClient.send("/app/chat", {}, JSON.stringify({ sender: sender, content: message }));
      setMessage("");
    }
  };

  return (
    <div style={{ padding: 20 }}>
      <h2>Real-Time Chat</h2>
      <input 
        type="text"
        value={sender}
        onChange={(e) => setSender(e.target.value)}
        placeholder="Type your username to chat or stay unknown"
        />
      <div style={{ height: "200px", overflowY: "auto", border: "1px solid #ccc", padding: 10 }}>
        {messages.map((m, i) => (
          <div key={i}><strong>{m.sender}: </strong>{m.content}</div>
        ))}
      </div>
      <input
        type="text"
        value={message}
        onChange={(e) => setMessage(e.target.value)}
        onKeyDown={(e) => e.key === "Enter" && sendMessage()}
        placeholder="Type a message..."
        style={{ width: "80%", marginTop: 10 }}
      />
      <button onClick={sendMessage} style={{ marginLeft: 10 }}>Send</button>
    </div>
  );
}
