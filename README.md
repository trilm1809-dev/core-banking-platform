# Core Banking Platform

## Project Overview

Core Banking Platform is a web-based Digital Banking Platform that enables customers to perform essential banking operations such as managing bank accounts, transferring money, managing beneficiaries, viewing transaction history, and scheduling recurring payments.

The platform also provides an administration portal for bank employees and administrators to manage customers, bank accounts, and monitor system activities.

This project is designed to simulate the core functionalities of a modern banking system, with a strong focus on:

* Transaction Processing
* Account Management
* Security
* Auditability
* Banking Business Rules

The goal of this project is not to build a full-scale enterprise Core Banking System, but rather to implement the most important banking functionalities to demonstrate backend development, system design, and business domain modeling skills using Spring Boot.

---

# Actors & Features

## 1. Customer

Customers use online banking services through the platform.

### Features

* Authentication & Authorization
* View Customer Profile
* Manage Bank Accounts
* Money Transfer
* Transaction History
* Beneficiary Management
* Scheduled Payments
* Security Center

---

## 2. Teller

Bank employees who support customers and manage banking operations.

### Features

* Create Customer
* Open Bank Account
* View Customer Information
* Lock / Unlock Accounts
* Monitor Transactions
* Customer Support

---

## 3. Admin

System administrators responsible for managing the entire platform.

### Features

* User Management
* Role & Permission Management
* Audit Monitoring
* Fraud Monitoring
* System Administration

---

# Core Modules

## Module 1 – Authentication & Authorization

### Features

* Login
* Logout
* Current User Information
* Change Password
* Role-Based Access Control (RBAC)

### Purpose

Ensures users are authenticated and authorized to access only the functionalities assigned to their roles.

---

## Module 2 – Customer Management

### Features

* Create Customer
* Update Customer
* Search Customers
* View Customer Details
* Delete Customer

### Purpose

Manages customer information within the banking system.

---

## Module 3 – Bank Account Management

### Features

* Open Bank Account
* Update Account Information
* Close Account
* View Account Details
* Search Accounts

### Purpose

Manages customer bank accounts and account lifecycle operations.

---

## Module 4 – Money Transfer

### Features

* Internal Fund Transfer
* Balance Validation
* Transfer Validation
* Transaction Recording

### Purpose

Processes money transfers between bank accounts.

### Business Rules

* Sufficient Balance Validation
* Active Account Validation
* Active Beneficiary Validation
* Transaction Auditing

---

## Module 5 – Transaction History

### Features

* Search Transactions
* View Transaction Details
* Generate Account Statements

### Purpose

Allows customers to review and track their transaction history.

---

## Module 6 – Beneficiary Management

### Features

* Add Beneficiary
* Update Beneficiary
* Delete Beneficiary
* Search Beneficiaries

### Purpose

Manages frequently used recipients for faster and more secure money transfers.

---

## Module 7 – Scheduled Payment

### Features

* Create Scheduled Payments
* Pause Scheduled Payments
* Resume Scheduled Payments
* Cancel Scheduled Payments
* Automatic Payment Execution

### Purpose

Allows customers to schedule recurring payments such as:

* Monthly Rent
* Loan Repayments
* Tuition Fees
* Savings Transfers

### Technical Highlights

* Spring Scheduler (@Scheduled)
* Background Job Processing
* Reusable Transfer Service
* Automatic Transaction Creation

---

## Module 8 – Fraud Detection

### Features

* Large Transfer Detection
* Rapid Transfer Detection
* New Beneficiary Detection

### Fraud Rules

#### Large Transfer

Transfer amount exceeds 100,000,000 VND.

#### Rapid Transfer

More than five transfers are performed within five minutes.

#### New Beneficiary

The beneficiary was created within the last 24 hours.

### Purpose

Helps reduce the risk of suspicious or potentially fraudulent transactions.

---

## Module 9 – Audit Timeline

### Features

* Audit Customer Actions
* Audit Transfer Activities
* Audit Beneficiary Activities
* Audit Security Events
* Audit Scheduled Payments

### Purpose

Provides a complete audit trail for monitoring, compliance, and troubleshooting purposes.

---

## Module 10 – Security Center

### Features

* Change Password
* Change Transaction PIN
* Account Locking
* Security Auditing

### Purpose

Provides customers with tools to manage and improve account security.

---

# System Architecture

```text
React Application
        |
        v
REST API
(Spring Boot)
        |
        v
Service Layer
(Business Logic)
        |
        v
Spring Data JPA
        |
        v
MySQL Database
```

---

# Backend Architecture

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
```

### Design Principles

* Layered Architecture
* Separation of Concerns
* DTO Pattern
* Repository Pattern
* Service Pattern
* Role-Based Access Control (RBAC)

---

# Database Design

## Main Tables

```text
users

roles

customers

bank_accounts

beneficiaries

transactions

scheduled_payments

fraud_alerts

audit_logs
```

## Relationships

```text
Customer
    └── BankAccount

Customer
    └── Beneficiary

BankAccount
    └── Transaction

BankAccount
    └── ScheduledPayment

Transaction
    └── FraudAlert
```

---

# Technology Stack

## Backend

* Java 21
* Spring Boot 3
* Spring Security
* Spring Data JPA
* Hibernate
* MySQL
* JWT Authentication
* Bean Validation
* Lombok
* MapStruct
* Maven

---

## Frontend

* ReactJS
* TypeScript
* Zustand
* Axios
* React Router
* Tailwind CSS

---

## Database

* MySQL

---

## Development Tools

* IntelliJ IDEA
* Visual Studio Code
* Postman
* Git
* GitHub

---

# Key Technical Highlights

## Transaction Processing

Ensures data consistency and reliability during money transfer operations.

## Scheduled Background Jobs

Implements recurring payment execution using Spring Scheduler.

## Fraud Detection

Applies a rule-based fraud detection mechanism to identify suspicious activities.

## Audit Trail

Tracks critical user and system actions for monitoring and traceability.

## Security

Implements authentication, authorization, and transaction-level security controls.

---

# Future Enhancements

* Notification Center
* Spending Analytics
* Multi-Factor Authentication (MFA)
* Email Notifications
* Analytics Dashboard
* External Bank Transfers
* Docker Deployment
* CI/CD Pipeline

---

# Learning Outcomes

Through this project, I gained practical experience in:

* Spring Boot Application Development
* RESTful API Design
* Authentication & Authorization
* Banking Domain Modeling
* Transaction Processing
* Background Job Processing
* Audit Logging
* Fraud Detection
* Relational Database Design
* Clean Architecture Principles
* Full-Stack Development with Spring Boot and React
