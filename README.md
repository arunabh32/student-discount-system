# 🎓 Student Discount System (Java)

A simple Java command-line application that records student information and calculates discounted course prices based on full-time or part-time status. This project demonstrates clean OOP design using abstract classes, interfaces, and inheritance.

---

## 📌 Features

- 🧑‍🎓 Store student info: name, roll number, subject, stream, and status
- 💸 Apply course fee discounts:
  - 40% for part-time students
  - 30% for full-time students
- ✅ CLI input validation using exception handling
- 📦 Store multiple student entries using `ArrayList`

---

## 💻 Tech Stack

- **Language:** Java
- **Concepts Used:**  
  `OOP`, `Interfaces`, `Abstract Classes`, `Exception Handling`
- **Tools:** Git, GitHub, VS Code or any IDE

---

## 📁 Project Structure

```plaintext
Rew.java             --> Main class that drives the app
Student              --> Abstract base class
Batch                --> Inherits Student and implements DisplayInfo
CourseCost           --> Inherits Batch and implements Displaydiscount
DisplayInfo          --> Interface to print student name/roll
Displaydiscount      --> Interface to apply discounts
