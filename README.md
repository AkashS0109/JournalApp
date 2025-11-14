# 📓 **JournalApp – Spring Boot Application**

A secure and scalable Spring Boot application for managing journal entries, featuring JWT authentication, Redis caching, Kafka event streaming, and automated weekly email reports using SMTP.

---

## 🚀 **Features**
- 🔐 JWT-based login & authentication  
- 👤 Role-based access (User/Admin)  
- 📝 CRUD for journal entries  
- ⚡ Redis caching for performance  
- 🔊 Kafka producer (events on signup & journal creation)  
- ⏰ Weekly scheduled email summary using Spring Scheduler  
- ✉️ SMTP email sending (Gmail/Yahoo/Outlook supported)  
- 🌦️ Optional Weather API integration  
- 🧪 Unit tests included  

---

## 🛠 **Tech Stack**

### **Backend**
- **Java 17**
- **Spring Boot 3**
- Spring Web  
- Spring Data JPA  
- Spring Security  
- Spring Mail (SMTP)  
- Spring Scheduler  
- **Apache Kafka**  
- **Redis Cache**  

### **Database**
- MySQL / PostgreSQL  

### **Build Tool**
- Maven  

---

## 📂 **Project Structure**
```
journalApp/
 ├── src/main/java/com/ranchopro/journalApp
 │    ├── controller/
 │    ├── service/
 │    ├── repository/
 │    ├── entity/
 │    ├── config/
 │    ├── scheduler/
 │    ├── utils/
 │    ├── api/
 │    └── JournalAppApplication.java
 ├── src/test/
 ├── pom.xml
 └── README.md
```

---

## ⚙️ **Setup Instructions**

### **1️⃣ Clone the Repository**
```bash
git clone https://github.com/AkashS0109/JournalApp.git
cd journalApp
```

---

## 🧾 **2️⃣ Configure `application.yml`**

> ⚠️ **Do NOT commit your real credentials.**  
> Push only an example file like `application-example.yml`.

```yaml
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

redis:
  host: localhost
  port: 6379
```

---

## 🔊 **3️⃣ Kafka Setup (Local)**

Start **Zookeeper**:
```bash
zookeeper-server-start.sh config/zookeeper.properties
```

Start **Kafka Broker**:
```bash
kafka-server-start.sh config/server.properties
```

---

## 🚀 **4️⃣ Run the Application**

### Using Maven
```bash
mvn spring-boot:run
```

### OR Build JAR
```bash
mvn clean package
java -jar target/journalApp-0.0.1.jar
```

---

## 🧪 **5️⃣ Run Tests**
```bash
mvn test
```

---

## ✉️ **Weekly Email Summary**

A Spring Scheduler job automatically sends a **weekly journal summary email**:

```java
@Scheduled(cron = "0 0 0 * * SUN")
public void sendWeeklySummary() {
    // Logic to send weekly summary email
}
```

---

## 🔐 **Authentication Flow**

```
User Login → Server validates → Returns JWT
↓
Client sends JWT in Authorization Header
↓
JwtFilter checks token on every request
↓
Access granted/denied
```

---

## 👨‍💻 **Author**
**Akash Singh**  
Full Stack Developer | Java | Spring Boot | React | Redis | Kafka  

---

## 📄 **License**
MIT License

