Great! Including Docker configuration in your README is essential for helping users and developers quickly get your application up and running. Here is an updated README file that includes instructions for using Docker and Docker Compose:

---

# FairShare

FairShare is a web application designed to help users manage group expenses easily. Users can create groups, add participants, and track shared expenses.

## Table of Contents
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Installation](#installation)
- [Usage](#usage)
- [Docker](#docker)

## Features
- User registration and login
- Group creation and management
- Adding participants to groups
- Adding and managing expenses within groups
- Searching for participants
- View balances 

## Technology Stack
- **Backend**: Spring Boot, Spring Data JPA
- **Frontend**: Thymeleaf, HTML, CSS, JavaScript
- **Database**: MySQL 
- **Build Tool**: Maven
- **Containerization**: Docker, Docker Compose

## Installation
### Prerequisites
- Java 17 or higher
- Maven
- MySQL 

### Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/fairshare.git
   cd fairshare
   ```

2. Configure the database:
   - Update the `application.properties` file with your database configuration.

3. Build the project:
   ```bash
   mvn clean install
   ```

4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

5. Open your browser and navigate to `http://localhost:8080`.

## Usage
### User Registration
1. Navigate to the registration page.
2. Fill out the registration form and submit.

### Creating a Group
1. Log in to your account.
2. Navigate to the group creation page.
3. Fill in the group details and add participants.
4. Click "Create Group" to save.

### Adding an Expense
1. Select a group.
2. Navigate to the "Add Expense" page.
3. Fill in the expense details and submit.

## Balances Feature
The balances feature allows users to view:
- Who owes whom and how much
- The total expenses paid by each participant

### Viewing Balances
1. Select a group.
2. Navigate to the "Balances" page.
3. The page will display a summary of who owes whom and the total expenses paid by each participant.


## Docker
### Prerequisites
- Docker
- Docker Compose

### Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/fairshare.git
   cd fairshare
   ```

2. Build and run the application using Docker Compose:
   ```bash
   docker-compose up --build
   ```

3. The application will be available at `http://localhost:1111`.

