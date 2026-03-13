# student-management-system-java
Student Management System built using Core Java and ArrayList. This console application allows adding, viewing, updating and deleting student records.
# Student Management System (Core Java)

This is a console based project built using Core Java.

Features:
- Add Student
- View Student
- Update Student
- Delete Student

Technologies Used:
- Java
- ArrayList
- OOP
- Console Application

# Employee Management System (Core Java + JDBC)

## Project Overview
This is a simple **Employee Management System** built using **Core Java** and **JDBC** with **PostgreSQL** database.  
The system allows you to **Add, View, Update, and Delete employees**. This project is perfect for beginners to showcase their **Java and Database skills**.

---

## Features
- Add new employee with **ID, Name, Salary, and Department**
- View all employees in the database
- Update employee details by ID
- Delete employee by ID
- Simple and **easy-to-use console interface**

---

## Technologies Used
- **Java (Core Java)**
- **JDBC** (Java Database Connectivity)
- **PostgreSQL** Database
- **Scanner & Console-based interface**

---

## Database Setup
1. Install **PostgreSQL** and create a database named `Students`.
2. Create the `employee` table:

```sql
CREATE TABLE employee (
    emp_id INT PRIMARY KEY,
    emp_name VARCHAR(50) NOT NULL,
    emp_salary DOUBLE PRECISION NOT NULL,
    department VARCHAR(50) NOT NULL
);

------------------------------------------------------------------------------------------------------------------------------------------------------

#HR Management System

A console-based HR management application built using Java, JDBC and PostgreSQL.

Features
- Add Department
- Add Employee
- View All Employees
- Department Wise Employees
- Highest Salary Employee
- Search Employee
- Update Employee
- Delete Employee

Technologies
- Java
- JDBC
- PostgreSQL
