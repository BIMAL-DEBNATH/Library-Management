# Library-Management
A library management project is a software application that helps to manage the operations and functions of a library. It can be used by librarians, staff, and patrons to perform tasks such as cataloging, searching, borrowing, returning, and reserving books and other materials. Here is a possible description for a library management project:
This project is a library management system that provides a user-friendly interface and a database for storing and retrieving information about books, users, and transactions in a library. It allows librarians, staff, and patrons to access and manage the library resources efficiently and effectively.

![image](https://github.com/BIMAL-DEBNATH/Library-Management/assets/131388946/7168c22e-3de5-494b-8a8e-2a478b487728)

## Features

- **Book Management:** The system allows librarians and staff to add, edit, delete, and view books and their details such as title, author, publisher, ISBN, category, status, and location. The system also generates barcodes for each book for easy identification and tracking.
- **User Management:** The system allows librarians and staff to add, edit, delete, and view users and their details such as name, email, phone number, address, role, and password. The system also assigns a unique ID for each user for authentication and authorization purposes.
- **Transaction Management:** The system allows librarians, staff, and patrons to perform transactions such as borrowing, returning, and reserving books. The system records the transaction details such as book ID, user ID, date, time, and status. The system also enforces the rules and policies of the library such as loan period, fine amount, maximum number of books per user, etc.
- **Search Function:** The system allows librarians, staff, and patrons to search for books by using various criteria such as title, author, publisher, ISBN, category, status, or keyword. The system returns a list of matching books with their details and availability.

## Technologies

- **Java:** The programming language used to develop the front-end and back-end of the system.
- **MySQL:** The relational database management system used to store and manage data for the system.
- **JDBC:** The Java Database Connectivity API used to connect and interact with the database from the Java code.

## Installation
https://github.com/BIMAL-DEBNATH/Library-Management/new/master?readme=1
To run this project locally, you need to have Java 19 or higher, MySQL 5.7 or higher, and Eclipse IDE installed on your machine. You also need to create a database named `library` in MySQL and import the `library.sql` file from the `src/main/resources` folder of the project. Then follow these steps:

1. Clone this repository using `git clone https://github.com/BIMAL-DEBNATH/Library-Management.git`.
2. Open Eclipse IDE and import the project as an existing Maven project.
3. Run `mvn clean install` to build the project and resolve the dependencies.
4. Run `LibraryManagement.java` as a Java application to start the system.
5. Use the default username `admin` and password `admin` to log in as a librarian or staff. Use any user ID from the database to log in as a patron.

## License

This project is licensed under the MIT License - see the [LICENSE] file for details.

