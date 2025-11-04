🌍 E-Tour – Travel Booking Platform 

🖥 Tech Stack

Frontend: React JS

Backend: Spring Boot 3 (Java 17) / ASP.NET Core 8 (C#)

Database: MySQL 8 / SQL Server

Authentication: JWT Token

Architecture: REST API + Layered Architecture

📖 Project Overview

E-Tour is a B2C web application designed to simplify the process of exploring and booking domestic, international, and event-based tours.

Users can easily:

Browse tour categories and packages.

View itineraries, costs, and departure dates.

Register, log in, and book tours with passenger details.

Get booking summaries and payment status.

The system ensures secure, fast, and scalable communication between frontend and backend using REST APIs.

✨ Key Features

🔹 User authentication (Sign-Up / Login with JWT)

🔹 Browse tours by category and subcategory

🔹 Detailed package view (itinerary, cost, departures)

🔹 Passenger-wise booking summary

🔹 Email confirmation on booking

🔹 Responsive React UI for smooth user experience

⚙️ Installation
1️⃣ Clone Repository
git clone https://github.com/prathmeshpachange/eTour.git
cd eTour

2️⃣ Backend Setup
For Spring Boot:
cd backend
mvn clean install
mvn spring-boot:run

For .NET Core:
cd backend-dotnet
dotnet build
dotnet run

3️⃣ Frontend Setup
cd frontend
npm install
npm start


Then open 👉 http://localhost:3000

📊 Database Overview

Main Tables:
Category, Subcategory, Package, Itinerary, Cost, Departure, Customer, Booking, Passenger

Relationships:

One Category → Many Subcategories

One Subcategory → Many Packages

One Package → Many Itineraries, Costs, Departures

One Customer → Many Bookings

One Booking → Many Passengers

🧩 Design Patterns Used

Repository Pattern – separates database logic

DTO Pattern – for clean data transfer

Dependency Injection (DI) – for service management

Mapper Pattern (AutoMapper/ModelMapper) – for entity-DTO mapping

Middleware Pattern – for authentication and error handling

👨‍💻 Author

Prathmesh Pachange
Team Lead | Full Stack Developer
📍 Mumbai, India
🔗 LinkedIn
 | GeeksforGeeks
