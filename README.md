# 🚀 Spring AI Prompt Template Enquiry Assistant

## 📌 Overview
This project is a Spring Boot application demonstrating the use of **Spring AI PromptTemplate** with LLM integration to handle student enquiries for an online learning platform.

The system processes incoming student queries and generates:
- A summarized understanding of the enquiry
- A polite and professional response
- Intent classification (for business handling)

---

## 🏫 About THE ONLine-ACADEMY SCHOOL
THE ONLine-ACADEMY SCHOOL is an online platform founded by **Ashish Mohite**, offering courses in:

- AI implementation using Spring AI & Spring Boot
- Microservices
- Angular
- React

💰 Course Fee Range: ₹10,000 – ₹30,000  
💳 Payment Modes: Cash, Card, UPI, PayPal

---

## 🎯 Use Case
This system automates handling of student enquiries such as:
- Course-related questions
- Technical support queries
- Payment/refund issues
- General enquiries

---

## 🧠 Prompt Template Logic

The application uses a structured **PromptTemplate** to:

1. Understand the student enquiry
2. Generate a polite, friendly response
3. Classify the intent into predefined categories

### 🔹 Intent Categories
- `Course Enquiry`
- `technical_support`
- `payment_or_refund`
- `other`

---

## 📥 Input Example
```json
example-1:
{
  "studentName": "John Doe",
  "studentEmail": "john@example.com",
  "enquiryMessage": "Can you share details about AI course fees and duration?"
}
example-2:
{
  "studentName": "John Doe",
  "studentEmail": "john@example.com",
  "enquiryMessage": "how can i make payment for the courses?"
}