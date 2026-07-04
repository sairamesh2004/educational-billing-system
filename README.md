# Educational Billing System

A desktop-based Educational Billing System developed using Java Swing, JDBC, and Oracle Database. The application enables administrators and employees to manage student records, fee details, and generate PDF invoices through an easy-to-use graphical interface.

This project was developed as part of my Java learning journey to gain hands-on experience with desktop application development, database connectivity, and CRUD operations.

---

## Features

- Employee Login
- Admin Login
- Student Registration
- Student Information Management
- Update Student Details
- View All Student Records
- Fee Details Management
- Student Search
- PDF Invoice Generation
- Oracle Database Integration
- JDBC Connectivity
- Java Swing GUI

---

## Tech Stack

### Programming Language

- Java

### GUI Framework

- Java Swing
- AWT

### Database

- Oracle Database XE

### Connectivity

- JDBC

### PDF Library

- iText PDF

---

## Project Structure

```
educational-billing-system/
│
├── src/
│   ├── AdminLogin.java
│   ├── AdminPortal.java
│   ├── EmployeeLogin.java
│   ├── EmployeePortal.java
│   ├── StudentRegistration.java
│   ├── StudentInfo.java
│   ├── UpdateStudentDetails.java
│   ├── AllStudentDetails.java
│   ├── FeeDetails.java
│   ├── GenerateInvoice.java
│   ├── MAIN.java
│   └── ...
│
├── assets/
│   ├── Student.png
│   ├── Home Page.png
│   ├── padlock.png
│   ├── arrow.png
│   ├── left-arrow.png
│   ├── left-arrow-1.png
│   ├── personal-file.png
│   ├── contact-form.png
│   ├── student info.png
│   ├── receipt.png
│   ├── button_background.png
│   └── enter_button2.png
│
├── database/
│   └── README.md
│
├── README.md
├── LICENSE
└── .gitignore
```

---

## Modules

### Authentication

- Admin Login
- Employee Login

### Student Management

- Register Student
- Update Student Details
- View Student Information
- Search Student
- Display All Students

### Fee Management

- View Fee Details
- Payment Status Tracking

### Invoice Management

- Generate Student Invoice
- Export Invoice as PDF

---

## Database

This project uses **Oracle Database XE** with JDBC.

The following tables are required:

- EmployeeDetails
- StudentDetails

Create the required tables before running the application and update the Oracle JDBC connection details according to your local database configuration.

---

## Installation

### 1. Clone the repository

```bash
git clone https://github.com/sairamesh2004/educational-billing-system.git
```

### 2. Open the project

Open the project in:

- Eclipse IDE
- IntelliJ IDEA
- NetBeans

### 3. Configure Oracle Database

Install Oracle Database XE.

Update the JDBC connection details inside the source files if necessary.

Example:

```java
DriverManager.getConnection(
    "jdbc:oracle:thin:@localhost:1521:xe",
    "system",
    "root"
);
```

### 4. Add JDBC Driver

Download the Oracle JDBC Driver and add it to your project's Build Path.

### 5. Add iText Library

Download the iText PDF library and add it to your project's Build Path.

### 6. Run

Execute the application's entry class.

```
MAIN.java
```

---

## Future Improvements

- User password encryption
- Email invoice support
- Dashboard analytics
- Better UI/UX
- Search filters
- Database configuration file
- MySQL/PostgreSQL support
- Spring Boot Web Version

---

## Learning Outcomes

Through this project, I learned:

- Java Swing GUI development
- Event-driven programming
- JDBC database connectivity
- CRUD operations
- Oracle Database integration
- SQL queries
- Form validation
- PDF generation using iText
- Object-Oriented Programming concepts

---

## License

This project is licensed under the MIT License.
