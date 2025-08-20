# 🏋️ Welcome to Fitness Management System 

This is the official documentation hub for **Fitness Management System** — a Java-based platform that connects **admins**, **instructors**, and **clients** to manage **fitness programs, subscriptions, and wellness tracking**.

---

## Authors
- Osama Ishtaiwi  
- Saif Shayep  

---

## Core Features

| Role          | Features |
|---------------|-----------|
| **Admin**     | Manage users (add/update/deactivate), approve instructor registrations, manage subscriptions (Basic/Premium), monitor user activity, generate reports (revenue, attendance, progress), approve/reject wellness content |
| **Instructor** | Create/update/delete fitness programs, add details (title, duration, difficulty, goals, media, price), schedule group sessions, interact with clients (messages/forums), track client progress, send announcements |
| **Client**    | Create and customize profiles, browse and enroll in programs, track progress (BMI, milestones), review programs, submit feedback, earn achievements and badges |

---

## System Architecture

The system is designed with a **modular layered architecture**, ensuring scalability, maintainability, and performance:

- **Core Features Layer**: Implements user, program, and subscription management.  
- **Data Layer**: Uses JSON files for lightweight persistence.  
- **Report Layer**: Generates attendance, revenue, and progress reports.  
- **Testing Layer**: Validates outputs and stores testing data.  

---

# 🗂 Database (JSON Data Model)

The system uses **JSON files** to simulate lightweight database storage:

| File Name           | Description |
|----------------------|-------------|
| **accounts.json**    | Stores user accounts (Admin, Instructor, Client) |
| **Programs.json**    | Fitness program details |
| **Enrolls.json**     | Tracks client enrollments |
| **messages.json**    | Stores private and forum messages |
| **Notifications.json** | Notifications for users |
| **schedules.json**   | Program and session schedules |
| **Users.json**       | Client profiles and personal details |
| **forums.json**      | Community discussions |
| **Reviews.json**     | Program reviews and ratings |

---

## Technology Used

| Tool/Library   | Category   | Purpose |
|----------------|-----------|---------|
| **Java**       | Programming Language | Implements the system logic with OOP principles |
| **Maven**      | Build Tool | Dependency management & project build |
| **JSON**       | Data Layer | Lightweight data persistence |
| **IntelliJ IDEA** | IDE | Development and debugging |

---

## Testing & Reports

- **`output/`** → Generated output data  
- **`outputTesting/`** → Testing and validation results  
- **`reports/`** → Analytical reports (attendance, revenue, progress)  

---

## Future Enhancements

- Replace JSON with a relational database (**MySQL/SQLite**).  
- Add **Spring Boot REST APIs** for web/mobile integration.  
- Integrate **RFID/QR login** for gym entry.  
- Use **AI-driven recommendations** for personalized programs.  

---

## Highlights

- Role-based system (**Admin, Instructor, Client**)  
- Progress & subscription tracking  
- Modular architecture for easy scaling  
- JSON-based lightweight database  
- Reports and analytics for decision-making  
