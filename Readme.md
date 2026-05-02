Hotel Reservation System (Java + JDBC)

This is a console-based Hotel Reservation System built using Java and JDBC.
The application connects to a MySQL database and allows users to manage hotel bookings through basic CRUD operations.

--------------------------------------------------

Features

- Reserve a room
- View all reservations
- Get room number using reservation ID
- Update reservation details
- Delete reservation

--------------------------------------------------

Technologies Used

- Java
- JDBC (Java Database Connectivity)
- MySQL

--------------------------------------------------

Setup Instructions

1. Create Database

Run the following commands in MySQL:

CREATE DATABASE hotel_db;

USE hotel_db;

CREATE TABLE reservations (
reservation_id INT AUTO_INCREMENT PRIMARY KEY,
guest_name VARCHAR(100),
room_number INT,
contact_number VARCHAR(15),
reservation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

--------------------------------------------------

2. Update Database Credentials

Open Main.java and update:

private static final String URL = "jdbc:mysql://localhost:3306/hotel_db";
private static final String USER = "root";
private static final String PASSWORD = "your_password";

--------------------------------------------------

3. Add MySQL Connector

Download MySQL Connector/J and add the JAR file to your project libraries.

--------------------------------------------------

4. Run the Application

Run Main.java from your IDE.

--------------------------------------------------

Sample Output

===== HOTEL MANAGEMENT SYSTEM =====
1. Reserve Room
2. View Reservations
3. Get Room Number
4. Update Reservation
5. Delete Reservation
0. Exit

--------------------------------------------------

Project Structure

Main.java
README.md

--------------------------------------------------

Future Improvements

- Add room availability validation
- Use PreparedStatement for secure queries
- Convert into Spring Boot application
- Add user interface (GUI or web)

--------------------------------------------------

Author

Anushka Thakkar