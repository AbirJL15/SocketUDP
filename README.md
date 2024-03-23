


# Sockets Module :  Group Chat Application
## Overview
This module implements a simple group chat application where multiple clients can connect to a server and exchange messages in a chat room setting.

## Features
- **Server**: The server is responsible for accepting incoming client connections and facilitating communication between clients.
- **Client**: Clients can connect to the server, send messages to the chat room, and receive messages from other clients.
- **Multi-threading**: Utilizes multi-threading to handle multiple connections simultaneously.
- **Username**: Each client can choose a username, which is displayed along with their messages in the chat.

## Files
- **Server.java**: Contains the server-side logic for accepting client connections and managing communication.
- **Client.java**: Implements the client-side functionality for connecting to the server, sending messages, and receiving messages.
- **ClientHandler.java**: Represents a thread handling communication with a specific client on the server-side.
- **README.md**: This file. Provides an overview of the project and instructions for running the application.

## How to Run
1. Compile all Java files using `javac *.java`.
2. Start the server by running `java Server`.
3. Run multiple instances of the client using `java Client`.
4. Enter a username for each client when prompted.
5. Start chatting!

