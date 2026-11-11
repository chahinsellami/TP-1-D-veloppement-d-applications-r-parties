# TP1 - Calculatrice TCP Client/Serveur

Projet de développement d'applications réparties (DAR) - Activités 1-1 à 1-3

---

## About

This is a TCP client-server calculator that communicates over a configurable port. The architecture allows multiple clients to connect in parallel, each receiving results in real-time.

## Structure
```
.
├── src/
│   ├── clientPackage/
│   │   └── Client.java
│   └── serverPackage/
│       └── Server.java
├── README.md
└── .gitignore
```

---

## Compilation

```powershell
javac -d out src\serverPackage\Server.java src\clientPackage\Client.java
```

---

## Launch

### Server
```powershell
java -cp out serverPackage.Server [port]
```
Port par défaut : 5000

### Client
```powershell
java -cp out clientPackage.Client [host] [port]
```
Host par défaut : localhost  
Port par défaut : 5000

### Cross-machine
```powershell
java -cp out clientPackage.Client 192.168.1.100 5000
```

---

## IDE & Execution

**Pure Java (JDK 8+), zero external dependencies.**

I developed using VS Code (same workflow as IntelliJ):

1. Compile:
   ```powershell
   javac -d out src\serverPackage\Server.java src\clientPackage\Client.java
   ```

2. Run server (terminal 1):
   ```powershell
   java -cp out serverPackage.Server 5000
   ```

3. Run client (terminal 2):
   ```powershell
   java -cp out clientPackage.Client localhost 5000
   ```

Try-with-resources (`try (Socket socket = ...)`) ensures clean, automatic file closure. The code runs identically in any IDE.

---
