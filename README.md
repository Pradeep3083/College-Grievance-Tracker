# College Grievance Tracker 🏛️

A web application designed to streamline grievance submission and management within a college environment, providing a platform for students to voice concerns and for administrators to track and resolve issues efficiently.

---

## Project Overview

This project aims to bridge the communication gap between students and college administration regarding grievances. It offers a user-friendly interface for students to submit various types of grievances (e.g., academic, facilities, conduct) and a comprehensive dashboard for administrators to review, manage, and update the status of these issues. The system prioritizes ease of use, real-time tracking, and secure access for different user roles.

---

## Features

* **User Authentication & Authorization**: Secure login and registration for students and administrators, with **role-based access control** to specific functionalities and dashboards.
* **Student Dashboard**:
    * Submit new grievances with details like type, description, and branch.
    * View a personalized list of all submitted grievances.
    * Track the **real-time status** of each grievance (Pending/Resolved).
    * Search and filter grievances for easy navigation.
* **Admin Dashboard**:
    * View all submitted grievances from all students, sorted by creation date (descending).
    * Update the status of grievances (e.g., from 'Pending' to 'Resolved').
    * View a list of all registered users in the system, sorted by creation date (descending).
* **Data Persistence**: Grievance and user data are securely stored in a MySQL database.
* **Responsive Design**: The application's interface adapts to various screen sizes, ensuring a consistent user experience on desktops, tablets, and mobile devices.

---

## Technologies Used

* **Spring Boot 3.3.5**: The core framework for building the robust, production-ready Spring-based application.
* **Java 23**: The programming language used for backend development.
* **Spring Data JPA**: For easy interaction with the MySQL database using Java Persistence API.
* **Spring Security**: Provides comprehensive security services, including authentication, authorization, and **password encoding**.
    * **BCryptPasswordEncoder**: Used for strong password hashing.
* **Thymeleaf**: A server-side Java template engine for creating dynamic HTML pages.
* **Spring Boot Starter Validation**: For form data validation.
* **MySQL Connector/J**: JDBC driver for connecting to MySQL databases.
* **Lombok**: Reduces boilerplate code (e.g., getters, setters, constructors).
* **Maven**: Dependency management and build automation tool.

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

* **Registration**: New users register as students through the `/register` page.
* **Login**: Students log in via the `/login` page, selecting the "Student" role.
* **Student Dashboard (`/student-dashboard`)**: After successful login, students are redirected to their personalized dashboard. From here, they can:
    * Submit new grievances.
    * View all their submitted grievances with their current status.
    * Search for specific grievances.

---

### Admin Role

* **Initial Admin Setup**: An admin user with email `admin@admin.com` and password `admin` is automatically created if not present on application startup by `AdminSetupService`.
* **Login**: Administrators log in via the `/login` page, selecting the "Admin" role.
* **Admin Dashboard (`/admin-dashboard`)**: After successful login, administrators are redirected to their dashboard. From here, they can:
    * View a comprehensive list of all grievances submitted by all students, sorted by creation date (descending).
    * Update the status of any grievance to "Pending" or "Resolved".
    * View a list of all registered users, sorted by creation date (descending).
