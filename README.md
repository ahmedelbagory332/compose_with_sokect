# Android Compose ChatApp with sokect


📌 Steps to Set Up Socket.IO with Node.js + Android (Using XAMPP)

1️⃣ Install Required Tools
- ✅ XAMPP (Apache, MySQL, PHP)
- ✅ Node.js (Download from nodejs.org)
- ✅ Android Studio (For the mobile app)
- ✅ Socket.IO (For real-time communication)

2️⃣ Install Node.js and Create WebSocket Server 

 📍 Step 1: Install Socket.IO
  
  - Open Command Prompt (cmd)
  - Navigate to the XAMPP htdocs folder:
```bash
cd C:\xampp\htdocs\socket_server
```
- Initialize a Node.js project:
```bash
npm init -y
```
- Install Socket.IO:
```bash
npm install express socket.io
```

📍 Step 2: Create WebSocket Server with Socket.IO

Create a new file:

📁 C:\xampp\htdocs\socket_server\server.js

```js
const express = require("express");
const http = require("http");
const socketIo = require("socket.io");

const app = express();
const server = http.createServer(app);
const io = socketIo(server, {
    cors: {
        origin: "*", // Allow all origins (Change this in production)
    }
});

io.on("connection", (socket) => {
    console.log(`New client connected: ${socket.id}`);

    socket.on("message", (data) => {
        console.log(`Received: ${data}`);
        io.emit("message", data); // Broadcast message to all clients
    });

    socket.on("disconnect", () => {
        console.log(`Client disconnected: ${socket.id}`);
    });
});

server.listen(3000, () => {
    console.log("WebSocket server running on ws://localhost:3000");
});
```
3️⃣ Run the Node.js WebSocket Server

- Open Command Prompt and go to socket_server:
```bash
cd C:\xampp\htdocs\socket_server
```
- Run the WebSocket server:
```bash
node server.js
```
- If everything is correct, you should see:
![image](https://github.com/user-attachments/assets/60f4d10d-dd5e-4cc4-abd6-c1e4700f64f2)


📱 Android Kotlin App (Jetpack Compose)

## The technology used:

- Jetpack Compose
- Code Architecture (MVVM)
- Clean Architecture  
- Dagger-hilt to handle dependency injection
- Co-routines to deal with threads



https://github.com/user-attachments/assets/efd5541d-7652-4fd6-b879-78706d2b9f98

