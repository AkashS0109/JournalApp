📓 JournalApp – Spring Boot Project

A secure and scalable Spring Boot application for managing personal journal entries with JWT authentication, Redis caching, Kafka events, and weekly email notifications through SMTP.

🔥 Features

User registration & login with JWT

CRUD operations for journal entries

Role-based access (User/Admin)

Redis Cache for fast data access

Kafka event publishing on user signup and journal creation

Weekly summary email sent via SMTP (Spring Mail)

Weather API integration (optional)

Unit tests included

🛠 Tech Stack

Java: 17

Spring Boot: 3.x

Spring Data JPA

Spring Security

Spring Mail (SMTP)

Redis

Apache Kafka

Maven

MySQL / PostgreSQL

⚙️ Setup Instructions
1️⃣ Clone the project
git clone <your-repo-url>
cd journalApp

2️⃣ Configure application.yml

Create this file:

server:
  port: 8080

spring:
  datasource:
    url: jdbc:mysql://localhost:3306/journaldb
    username: root
    password: yourpassword

  jpa:
    hibernate:
      ddl-auto: update

  mail:
    host: smtp.gmail.com
    port: 587
    username: your-email@gmail.com
    password: your-app-password
    properties:
      mail.smtp.auth: true
      mail.smtp.starttls.enable: true

spring:
  redis:
    host: localhost
    port: 6379


🚫 Do not push your real credentials!
Add only an example file application-example.yml.

🚀 Run the Application
Using Maven
mvn spring-boot:run

OR build jar
mvn clean package
java -jar target/journalApp-0.0.1.jar

🔊 Kafka Setup (if using locally)

Start Zookeeper:

zookeeper-server-start.sh config/zookeeper.properties


Start Kafka:

kafka-server-start.sh config/server.properties

🧪 Run Tests
mvn test

📁 Project Structure
src/
 ├── main/
 │    ├── java/com/ranchopro/journalApp
 │    ├── resources/
 └── test/

👨‍💻 Author

Akash Singh
Full Stack Developer – Java | Spring Boot | React | Redis | Kafka
