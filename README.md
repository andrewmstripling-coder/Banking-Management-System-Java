# Banking-Management-System-Java

A robust Java-based Banking Management System that showcases object-oriented programming (OOP) principles while providing comprehensive financial transaction processing, efficient account management, and advanced exception handling.

The application enables customer registration, supports both checking and savings accounts, and facilitates deposits, withdrawals, account transfers, and detailed transaction history tracking. Banking records are efficiently managed using Java collections, while BigDecimal ensures precise financial calculations.

Features

Customer Management

Register new customers with unique customer IDs.

Store customer contact information.

Search for customers by ID.

Prevent duplicate customer registrations.

Bank Account Management

Create checking and savings accounts.

Associate accounts with customer IDs.

Search for accounts using unique account numbers.

Prevent duplicate account registrations.

Track account balances and opening dates.

Checking Accounts

Support deposits and withdrawals.

Allow withdrawals within a configured overdraft limit.

Reject withdrawals exceeding the overdraft limit.

Apply monthly account maintenance fees.

Savings Accounts

Calculate monthly interest using an annual interest rate.

Apply earned interest to account balances.

Enforce minimum balance requirements.

Reject withdrawals that violate minimum balance restrictions.

Banking Transactions

Deposit funds into accounts.

Withdraw funds from accounts.

Transfer money between accounts.

Validate transaction amounts and account existence.

Handle invalid transactions using exceptions.

Transaction History

Automatically record successful deposits, withdrawals, and transfers.

Generate sequential transaction IDs such as T001, T002, and T003.

Store transaction types, account IDs, amounts, and timestamps.

Retrieve transaction history using a defensive copy of the internal collection.

Exclude rejected operations from successful transaction history.

Technologies Used

Language: Java

IDE: Eclipse

Programming Paradigm: Object-Oriented Programming

Collections: ArrayList

Financial Calculations: BigDecimal

Date and Time: LocalDate and LocalDateTime

Testing: Custom Java test suite with assertions and exception checks

Object-Oriented Programming Concepts

This project demonstrates the four fundamental principles of OOP.

Encapsulation: Banking data is stored in private fields and accessed through controlled methods.

Inheritance: CheckingAccount and SavingsAccount extend the abstract BankAccount class, inheriting common account properties and functionality.

Polymorphism: Checking and savings accounts override withdraw() to implement different withdrawal rules.

Abstraction: The abstract BankAccount class provides a shared foundation for different bank account types.

The project also demonstrates constructor validation, method overriding, exception handling, object composition, and collection management.

Project Structure

Banking-Management-System-Java/
└── src/
└── bankingmanagement/
├── Bank.java
├── BankAccount.java
├── Customer.java
├── CheckingAccount.java
├── SavingsAccount.java
├── Transaction.java
├── Main.java
└── Test.java

Class Descriptions

Class

Responsibility

Bank

Manages customers, accounts, and banking transactions

BankAccount

Abstract base class defining common account behavior

Customer

Stores customer information

CheckingAccount

Implements overdraft protection and monthly fees

SavingsAccount

Implements interest calculations and minimum balance rules

Transaction

Represents individual banking transaction records

Main

Demonstrates the application’s functionality

Test

Executes functional and exception-handling tests

Example Application Output

===== BANKING MANAGEMENT SYSTEM =====

Welcome to Community Bank

— ACCOUNTS CREATED —
Account: A001 | Balance: $1000.00
Account: A002 | Balance: $2000.00

— DEPOSIT —
Account: A001 | Balance: $1500.00

— WITHDRAWAL —
Account: A001 | Balance: $1300.00

— TRANSFER —
Account: A001 | Balance: $1000.00
Account: A002 | Balance: $2300.00

— TRANSACTION HISTORY —
ID: T001 | Account: A001 | Type: DEPOSIT | Amount: $500.00
ID: T002 | Account: A001 | Type: WITHDRAWAL | Amount: $200.00
ID: T003 | Account: A001 | Type: TRANSFER | Amount: $300.00

Total recorded transactions: 3

— FINAL ACCOUNT BALANCES —
Account: A001 | Balance: $990.00
Account: A002 | Balance: $2308.63

===== DEMONSTRATION COMPLETE =====

Output abbreviated for readability. Actual transaction records also include timestamps.

Testing

The application includes a custom Java test suite that validates core banking functionality, account-specific business rules, transaction history, and exception handling.

Test Coverage

Bank and customer creation

Customer and account registration

Duplicate customer and account prevention

Account searches

Deposits and withdrawals

Account transfers

Invalid and null transaction amounts

Insufficient funds handling

Checking account overdraft protection

Monthly checking fees

Savings account interest calculations

Minimum balance enforcement

Transaction history creation

Sequential transaction ID generation

Transaction timestamps and amounts

Rejected transaction handling

Defensive copying of transaction history

Test Results

=== TEST SUMMARY ===

Passed: 56
Failed: 0
Total: 56

All 56 tests passed successfully.

How to Run

Using Eclipse

Clone or download the repository.

Open Eclipse IDE.

Import the project into your workspace as a Java project.

Locate the bankingmanagement package.

Open Main.java.

Right-click and select Run As → Java Application.

To execute the test suite, open Test.java and select Run As → Java Application.

Using the Command Line

If the repository contains the source structure shown above, navigate to the project root and run:

javac -d out src/bankingmanagement/*.java
java -cp out bankingmanagement.Main

To run the tests:

java -cp out bankingmanagement.Test

A compatible JDK is required. Java 11 or later supports the language features used in the application.

Current Limitations

This project is a core Java educational application rather than a production banking platform.

Customer, account, and transaction data are stored in memory and are not persisted between runs.

Transfer records currently identify the sending account but do not separately store the receiving account.

Monthly checking fees and savings interest are applied directly through account methods and are not automatically recorded in bank-level transaction history.

Transfers do not yet provide full atomic transaction guarantees.

Transaction IDs are sequential within a bank instance and reset when a new instance is created.

Future Enhancements

Potential improvements include:

Database integration using PostgreSQL or MySQL.

Persistent storage for customers, accounts, and transactions.

Complete transfer records containing both sending and receiving accounts.

Atomic transaction processing and rollback support.

JUnit automated testing.

A Spring Boot REST API.

A graphical or web-based user interface.

Account statements and transaction filtering.

Project Purpose

This project was developed to strengthen practical Java programming and object-oriented software design skills.

It demonstrates how multiple classes collaborate to implement banking operations, enforce financial business rules, manage application data, and maintain transaction records.

The application serves as a foundation for future development involving databases, REST APIs, and enterprise Java technologies.
