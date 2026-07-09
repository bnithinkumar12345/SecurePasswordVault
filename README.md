# 🔐 Secure Password Vault

A secure console-based Password Manager built using **Java, JDBC, MySQL, Maven, BCrypt, and AES Encryption**.

The application allows users to securely store, manage, and retrieve their website credentials while protecting sensitive information using modern encryption and hashing techniques.

---

# 🚀 Features

## 👤 User Management
- User Registration
- User Login
- Forgot Password
- Change Master Password
- Multi-User Support

## 🔑 Credential Management
- Add Credential
- View Credentials
- Search Credential
- Update Credential
- Delete Credential

## 🔒 Security
- BCrypt Password Hashing
- AES Password Encryption
- Secure Authentication

## 🛠 Utility Features
- Password Generator
- Password Strength Checker
- Login History
- Statistics Dashboard
- Export Credentials to CSV

---

# 🛠 Technologies Used

- Java 17
- JDBC
- Maven
- MySQL
- BCrypt
- AES Encryption
- IntelliJ IDEA

---

# 📂 Project Structure

```
SecurePasswordVault
│
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── com.nithin.config
│   │   │   ├── dao
│   │   │   ├── menu
│   │   │   ├── model
│   │   │   ├── service
│   │   │   └── util
│   │   └── resources
│   └── test
│
├── pom.xml
└── README.md
```

---

# 🗄 Database

Database Name:

```
password_vault
```

Tables:

- users
- credentials
- login_history

---

# ▶️ How to Run

### Clone the repository

```bash
git clone https://github.com/bnithinkumar12345/SecurePasswordVault.git
```

### Open the project

Open the project in IntelliJ IDEA.

### Create the database

Create a MySQL database named:

```
password_vault
```

Import the SQL schema.

### Configure Database

Update `DBConnection.java`

```java
private static final String URL = "jdbc:mysql://localhost:3306/password_vault";
private static final String USERNAME = "root";
private static final String PASSWORD = "your_password";
```

### Run

Run:

```
Main.java
```

---

# 📸 Screenshots

Add screenshots here after running the application.

- Registration
- Login
- Password Vault
- Statistics Dashboard
- Login History
- Export CSV

---

# 🔮 Future Enhancements

- Spring Boot REST API
- Email OTP Authentication
- JavaFX GUI
- Cloud Database Support
- Docker Deployment
- JWT Authentication
- Role-Based Access Control

---

# 👨‍💻 Developer

**B. Nithin Kumar**

B.Tech CSE (Cyber Security)

CMR College of Engineering & Technology

Hyderabad, Telangana

---

# ⭐ If you like this project

Give this repository a ⭐ on GitHub.