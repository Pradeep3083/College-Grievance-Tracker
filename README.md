# College Grievance Tracker 🏛️

A web application designed to streamline grievance submission and management within a college environment, providing a platform for students to voice concerns and for administrators to track and resolve issues efficiently.

---

## Project Overview

This project aims to bridge the communication gap between students and college administration regarding grievances. It offers a user-friendly interface for students to submit various types of grievances (e.g., academic, facilities, conduct) and a comprehensive dashboard for administrators to review, manage, and update the status of these issues. The system prioritizes ease of use, real-time tracking, and secure access for different user roles.

---

## Features

* [cite_start]**User Authentication & Authorization**: Secure login and registration for students and administrators, with **role-based access control** to specific functionalities and dashboards[cite: 7, 8].
* **Student Dashboard**:
    * [cite_start]Submit new grievances with details like type, description, and branch[cite: 45, 49, 56, 57].
    * [cite_start]View a personalized list of all submitted grievances.
    * [cite_start]Track the **real-time status** of each grievance (Pending/Resolved)[cite: 69, 190, 420].
    * [cite_start]Search and filter grievances for easy navigation.
* **Admin Dashboard**:
    * [cite_start]View all submitted grievances from all students, sorted by creation date (descending)[cite: 15, 113].
    * [cite_start]Update the status of grievances (e.g., from 'Pending' to 'Resolved')[cite: 59, 192].
    * [cite_start]View a list of all registered users in the system, sorted by creation date (descending)[cite: 16, 125].
* [cite_start]**Data Persistence**: Grievance and user data are securely stored in a MySQL database[cite: 1, 66, 75].
* [cite_start]**Responsive Design**: The application's interface adapts to various screen sizes, ensuring a consistent user experience on desktops, tablets, and mobile devices[cite: 174, 233, 280, 335, 404].

---

## Technologies Used

* [cite_start]**Spring Boot 3.3.5**: The core framework for building the robust, production-ready Spring-based application.
* [cite_start]**Java 23**: The programming language used for backend development.
* [cite_start]**Spring Data JPA**: For easy interaction with the MySQL database using Java Persistence API.
* [cite_start]**Spring Security**: Provides comprehensive security services, including authentication, authorization, and **password encoding**[cite: 1, 6].
    * [cite_start]**BCryptPasswordEncoder**: Used for strong password hashing[cite: 6].
* [cite_start]**Thymeleaf**: A server-side Java template engine for creating dynamic HTML pages.
* [cite_start]**Spring Boot Starter Validation**: For form data validation.
* [cite_start]**MySQL Connector/J**: JDBC driver for connecting to MySQL databases.
* [cite_start]**Lombok**: Reduces boilerplate code (e.g., getters, setters, constructors).
* [cite_start]**Maven**: Dependency management and build automation tool.

---

## Getting Started

Follow these instructions to set up and run the project on your local machine.

### Prerequisites

Before you begin, ensure you have the following installed:

* **Java Development Kit (JDK) 23** or higher.
* **Maven** (usually included with Spring Boot setups).
* **MySQL Server** running on `localhost:3306`.
* An IDE like **IntelliJ IDEA** or **Eclipse** (recommended).

### Installation

1.  **Clone the repository:**
    ```bash
    git clone <your-repository-url>
    ```
2.  **Navigate to the project directory:**
    ```bash
    cd College-Grievance-Tracker
    ```

### Database Configuration

The application uses a MySQL database. Update the `src/main/resources/application.properties` file with your MySQL database credentials.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/Your_DB?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=Your_Password
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.ddl-auto=update
logging.level.org.springframework=DEBUG
logging.level.com.grievance=DEBUG
server.port=3000
```

## User Roles and Dashboards

The system supports two primary roles: **STUDENT** and **ADMIN**.

---

### Student Role

* [cite_start]**Registration**: New users register as students through the `/register` page[cite: 23].
* [cite_start]**Login**: Students log in via the `/login` page, selecting the "Student" role[cite: 36, 297].
* [cite_start]**Student Dashboard (`/student-dashboard`)**: After successful login, students are redirected to their personalized dashboard[cite: 40, 97]. From here, they can:
    * [cite_start]Submit new grievances[cite: 45, 49, 57].
    * [cite_start]View all their submitted grievances with their current status[cite: 44, 56].
    * [cite_start]Search for specific grievances[cite: 415, 432].

---

### Admin Role

* [cite_start]**Initial Admin Setup**: An admin user with email `admin@admin.com` and password `admin` is automatically created if not present on application startup by `AdminSetupService`[cite: 129, 130, 131, 132, 133, 134].
* [cite_start]**Login**: Administrators log in via the `/login` page, selecting the "Admin" role[cite: 36, 298].
* [cite_start]**Admin Dashboard (`/admin-dashboard`)**: After successful login, administrators are redirected to their dashboard[cite: 96]. From here, they can:
    * [cite_start]View a comprehensive list of all grievances submitted by all students, sorted by creation date (descending)[cite: 15, 58, 113].
    * [cite_start]Update the status of any grievance to "Pending" or "Resolved"[cite: 59, 192, 193].
    * [cite_start]View a list of all registered users, sorted by creation date (descending)[cite: 16, 125, 126].
