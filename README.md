# OTP-Online-Test-Portal
A web-based platform that allows students to take online tests with OTP-based authentication. This project is developed using Java (Spring Boot) and provides an easy-to-use interface for students and administrators.

📌 Features
✅ Secure OTP Authentication – Users receive an OTP via SMS for account verification.
✅ User Registration & Login – Only verified users can log in and take tests.
✅ Course Selection & Online Tests – Students can select subjects and take multiple-choice tests.
✅ Real-time Result Generation – Test results are displayed instantly after submission.
✅ Admin Panel – Admin can manage users, add/edit/delete questions, and monitor test results.

🛠 Tech Stack

Backend: Java (Spring Boot)
Frontend: HTML, CSS, JavaScript
Database: MySQL
OTP Service: Twilio / Fast2SMS API
		
🔗 How It Works?

User Registration:

The user registers by providing their mobile number.
An OTP (One-Time Password) is sent via SMS.
The user enters the OTP to verify their account.

Login Authentication:

Users can log in only after successful OTP verification.
Test-Taking Process:

Users can select their preferred subject and start a test.
Questions are randomly fetched from the database.
After submission, the results are displayed instantly.

Admin Features:

Manage users & authentication.
Add/Edit/Delete test questions.
Monitor student results & test activity.
