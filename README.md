# Java JDBC – Employee Database App

## 🔧 Description
A simple Java console app for managing employees using JDBC and MySQL.

## 🚀 Setup

### Database Setup

```sql
CREATE DATABASE employeesdb;
USE employeesdb;

CREATE TABLE employee (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    department VARCHAR(50),
    salary DOUBLE
);
```

### Prerequisites
- Java JDK
- MySQL or PostgreSQL (default is MySQL)
- MySQL JDBC Driver (add to classpath)

## 🛠 How to Run

1. Clone the repo:
```bash
git clone https://github.com/AdarshhRaj/JDBCEmployeeApp.git
cd JDBCEmployeeApp
```

2. Compile:
```bash
javac -cp .:mysql-connector-java.jar EmployeeApp.java
```

3. Run:
```bash
java -cp .:mysql-connector-java.jar EmployeeApp
```

> Replace `mysql-connector-java.jar` with the actual path to your JDBC driver.

## ✅ Features
- Add, View, Update, and Delete employees
- JDBC connection using `PreparedStatement` and `ResultSet`

## 🧑‍💻 Author
Adarsh Raj
