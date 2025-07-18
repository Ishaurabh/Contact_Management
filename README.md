# Contact_Management
📇 Contact Management System (Java + JDBC)

A simple Contact Management System built in Java using JDBC (Java Database Connectivity) and MySQL. The system allows users to add, view, update, and delete contact information from a MySQL database.

🛠️ Features

Add a new contact (Name, Phone, Email)

View all contacts

Update existing contact details

Delete contacts

Input validation and clean CLI interface

🧰 Technologies Used

Java SE

JDBC API

MySQL (Relational database)

MySQL Workbench or phpMyAdmin (for DB management)

CLI (Command Line Interface)

🗃️ Database Schema

Database Name: contactdbTable Name: contacts

CREATE DATABASE contactdb;

USE contactdb;

CREATE TABLE contacts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    phone VARCHAR(15) NOT NULL,
    email VARCHAR(100)
);

📁 Project Structure

ContactManager/
│
├── ContactManager.java       # Main class with menu and logic
├── DBConnection.java         # Database connection helper
├── Contact.java              # POJO class for Contact model
└── README.md                 # Project documentation

▶️ How to Run

Open in your preferred IDE (e.g., IntelliJ, Eclipse, VS Code with Java Extension).

Set up the database in MySQL as shown above.

Update database credentials in the code (DBConnection.java or directly inside ContactManager.java):

String URL = "jdbc:mysql://localhost:3306/contactdb";
String USER = "root";
String PASS = "your_password";

Compile and run:

javac ContactManager.java
java ContactManager

📝 Example Operations

Add Contact

View All Contacts

Update Contact by ID

Delete Contact by ID

Each option is available through a menu-driven interface.

📌 Future Improvements

GUI using Swing or JavaFX

Export contacts to CSV or Excel

Search functionality

Validation enhancements

🙋‍♂️ Author

Saurabh SinghEmail: saurabhsingh273001@gmail.com

📄 License

This project is licensed under the MIT License - see the LICENSE file for details.


