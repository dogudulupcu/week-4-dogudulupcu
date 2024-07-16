# Kredinbizde Application

Welcome to the Kredinbizde application repository! This repository contains the source code for the Kredinbizde service, which is a part of a larger microservices architecture.

## Overview

The Kredinbizde application consists of several services, including:

1. **Notification Service**: Responsible for handling notifications to users.
2. **Garantibank Service**: Interacts with the Garantibank API for loan applications.
3. **Kredinbizde Service**: The main service handling user interactions, loan applications, and other related functionalities.

## Technologies Used

- **Spring Boot**: Kredinbizde is built using the Spring Boot framework.
- **Netflix Eureka**: Utilized as a service registry for service discovery.
- **Spring Cloud Gateway**: Used as an API gateway to route requests to the appropriate service.
- **Spring Data JPA**: For data persistence.
- **AMQP (RabbitMQ)**: Used for asynchronous communication between the Notification Service and Kredinbizde Service.
- **Feign Client**: Utilized for making requests to external services, such as Garantibank.
- **Mockito**: A mocking framework used in testing to mock dependencies and verify interactions.

## Entities

The Kredinbizde service deals with various entities, including:

- **User**: Represents users of the application.
- **Loan**: Information about loans available or applied for.
- **Application**: Loan applications submitted by users.
- **Bank**: Details about banks involved in loan processes.
- **Credit Card**: Information about credit cards associated with users.
- **Campaign**: Details of promotional campaigns related to loans.

## How it Works

When a user submits a loan application through Kredinbizde, the application is processed as follows:

1. The Kredinbizde Service receives the loan application from the user.
2. If necessary, the application interacts with the Garantibank Service via Feign Client to process the loan application with Garantibank.
3. The Garantibank Service processes the application and returns an application created in the Garantibank system.
4. Kredinbizde receives the application created by Garantibank and proceeds with further actions, such as notifying the user about the status of their application.

## Setup

To run the Kredinbizde application locally, follow these steps:

1. Clone this repository to your local machine.
2. Navigate to the root directory of the project.
3. Make sure you have Docker installed and running.
4. Run `docker-compose up` to start the necessary services (Eureka, RabbitMQ, etc.).
5. Once the services are up, you can start each individual service (Notification Service, Garantibank Service, Kredinbizde Service) using your preferred IDE or build tools.

## Contributing

We welcome contributions from the community! If you find any issues or have suggestions for improvement, please feel free to open an issue or submit a pull request.
