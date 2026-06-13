# Fleet Management System

Fleet Management System is a backend REST API for managing freight logistics operations - tracking trucks, assigning drivers, planning trips and handling client orders.

## Tech Stack

- **Java 21**
- **Spring Boot 4.0**
- **Hibernate / JPA**
- **PostgreSQL 16**
- **Docker**
- **Maven**

## Features

- Manage trucks, drivers, logisticians and client companies
- Create and assign orders to trips
- Complete trips with automatic order status updates
- Transactional logic ensuring data consistency
- Fully containerized with Docker

## Quick Start

```bash
git clone https://github.com/SCRIPT69/fleet-management-system
cd fleet-management-system
cp .env.example .env
docker-compose up --build
```

API is available at `http://localhost:8080`

## Documentation

Full documentation is available in the [Wiki](https://github.com/SCRIPT69/fleet-management-system/wiki):

- [ER Diagram](https://github.com/SCRIPT69/fleet-management-system/wiki/ER-Diagram)
- [Relational Model](https://github.com/SCRIPT69/fleet-management-system/wiki/Relational-Model)
- [Database Setup](https://github.com/SCRIPT69/fleet-management-system/wiki/Database-Setup)
- [API Endpoints](https://github.com/SCRIPT69/fleet-management-system/wiki/API-Endpoints)
