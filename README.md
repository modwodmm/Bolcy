# Bolcy

<img width="256" height="256" alt="bolcy_" src="https://github.com/user-attachments/assets/d17a15d2-0bfb-4216-a87c-f29eaf76daa9" />

Bolcy is an open-source chatting platform written in Java.
A simple command-line (CLI) chat app made to strengthen my knowledge in java and to follow clean software engineering practices.

## Features:

1. Messaging between multiple clients.
2. Username based accounts.
3. Password Hashing using argon2.
4. Client-Server Architecture using Java sockets.
5. Json-based data storage.
6. Configurable host and port.
7. Executable builds for client and server.

## How to run:

### Server:
1. Download 'Bolcy-Server.zip'.
2. Extract the ZIP file.
3. Move the folder to server and run the 'Bolcy-Server-{version}.exe'.
4. A cmd panel should open and the server should start.

### Client:
1. Download 'Bolcy-Client.zip'.
2. Extract the ZIP file.
3. Open 'data/config.properties' and configure the host and port as needed.
4. Run 'Bolcy-Client-{version}.exe'.

## Core Technologies:
- JDK 25.0.2
- Gradle 9.5.1
- Spring Security Crypto
- Bouncy Castle
- Jackson
- Shadow

## Development:
This app will get further updates and different OS support.

This software is licensed under MIT License.
