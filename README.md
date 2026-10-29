# Student Grading System 🎓

A **JavaFX-based Student Grading System** with MySQL database integration for storing and managing student records. This project allows **grade calculations, dynamic scene switching, and interactive UI components**.

## 🚀 Features
- **User Authentication** (Login System)
- **Grade Calculation & Storage** (MySQL Database)
- **Interactive UI** (JavaFX & SceneBuilder)
- **Data Visualization** (Charts & Graphs)

## 🛠 Technologies Used
- **Java** (JavaFX, JDBC)
- **MySQL** (Database Management)
- **SceneBuilder** (UI Design)
- **Git & GitHub** (Version Control)

## 📌 Setup Instructions
1. Clone the repository:
   ```bash
   git clone https://github.com/dhirajdc200/StudentGradingSystem.git
   ```
2. Configure MySQL (see below), then run the app.

![Screenshot 2025-03-15 191910](https://github.com/user-attachments/assets/b888663e-5bd1-400a-acdd-c16a9a6e4cb3)

## Database configuration

`DBConnection.getConnection()` loads MySQL settings from a local `db.properties` file. Place that file in the project root or in `src/main/resources` (on the classpath). It is listed in `.gitignore`.

```bash
cp db.properties.example db.properties
```

Set `db.url`, `db.user`, and `db.password` in `db.properties` to your own MySQL values. `db.properties.example` contains placeholders only.

These environment variables override the file when they are set:

- `DB_URL` overrides `db.url`
- `DB_USER` overrides `db.user`
- `DB_PASSWORD` overrides `db.password`

Rotate any database password that was previously stored in this repository, and keep the new password only in your local `db.properties` or in the environment.

