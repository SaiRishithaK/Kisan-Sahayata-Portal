# 🌾 Kisan Sahayata Portal

A Java-based desktop application designed to assist farmers by providing a centralized platform for managing agricultural information, government schemes, crop details, market prices, loans, and notifications.

## 📖 Overview

Kisan Sahayata Portal aims to simplify farmers' access to essential agricultural services through an easy-to-use desktop application. The system helps users manage their farming activities digitally while keeping them updated with market trends and government support.

---

## ✨ Features

### 👤 Farmer Management
- Farmer Registration & Login
- Secure Profile Management
- Modify Personal Details
- Land Information Management

### 🌱 Crop Management
- Add Crop Details
- View Registered Crops
- Crop Information
- Kharif & Rabi Crop Support

### 💰 Loan Management
- Apply for Agricultural Loans
- View Loan Status
- Loan Notifications

### 📈 Market Prices
- Live/Updated Crop Market Prices
- Market Price Dashboard

### 🌿 Fertilizer & Subsidies
- Fertilizer Information
- Government Subsidy Details

### 🔔 Notifications
- System Notifications
- Loan Updates
- Agricultural Alerts

---

## 🛠️ Tech Stack

- **Language:** Java
- **GUI:** Java Swing
- **IDE:** NetBeans
- **Database:** MySQL
- **JDBC** for Database Connectivity

---

## 📂 Project Structure

```
src/
├── data/
│   ├── Database Connection
│   ├── Data Access Classes
│   └── Managers
│
├── model/
│   ├── Farmer
│   ├── Crop
│   ├── Loan
│   ├── Land
│   ├── Subsidy
│   └── Notification
│
├── threads/
│   ├── Market Price Thread
│   └── Notification Thread
│
├── ui/
│   ├── Login
│   ├── Dashboard
│   ├── Crop Management
│   ├── Loan Management
│   ├── Market Prices
│   ├── Notifications
│   └── Profile Management
│
└── main/
    └── Main.java
```

---

## 🚀 Installation

### Clone the Repository

```bash
git clone https://github.com/<your-username>/Kisan-Sahayata-Portal.git
```

### Open Project

Open the project in **NetBeans IDE**.

### Configure Database

1. Install MySQL.
2. Create the required database.
3. Update the database credentials inside:

```
src/data/DBConnection.java
```

### Run

Run:

```
src/main/Main.java
```

---

## 📸 Screenshots

You can add screenshots here:

- Login Page
- Dashboard
- Crop Management
- Loan Application
- Market Prices
- Notifications

---

## 🎯 Future Enhancements

- Weather Forecast Integration
- Government Scheme API Integration
- AI-based Crop Recommendation
- Disease Detection
- Mobile Application
- Multilingual Support
- SMS Notifications

---

## 👩‍💻 Author

**Sai Rishitha Kandhikonda**

B.Tech Information Technology  
Vasavi College of Engineering

---

## 📜 License

This project is intended for educational and learning purposes.
