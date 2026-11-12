# Employee Management CRUD Application on AWS

## 1. Project Overview

This project is an Employee Management CRUD (Create, Read, Update, Delete) application developed using Java Spring Boot, MySQL, HTML, CSS and JavaScript.

The application is deployed on AWS using a multi-tier architecture with separate frontend, backend and database layers.

The main objective of this project is to understand real-world application deployment, AWS networking, load balancing and communication between different application tiers.

## 2. Technology Stack

| Component | Technology |
|---|---|
| Frontend | HTML, CSS, JavaScript |
| Backend | Java 21, Spring Boot |
| Database | MySQL 8 |
| Web Server | Nginx |
| Cloud Platform | AWS |
| Compute | Amazon EC2 |
| Load Balancing | Application Load Balancer |
| Networking | VPC, Subnets, Internet Gateway |
| Security | Security Groups |

## 3. AWS Architecture

The application uses a VPC with two Availability Zones and public/private subnets.

- Application Load Balancer receives incoming HTTP traffic.
- Target Group registers the frontend EC2 instance.
- Nginx serves frontend files and forwards API requests to the backend.
- Spring Boot handles CRUD operations.
- MySQL stores employee records.
- Security Groups control communication between application tiers.

See the architecture diagram in `architecture/aws-architecture.mmd`.

## 4. Application Features

- Add a new employee.
- View all employees.
- Update employee details.
- Delete employee records.
- Store employee information in MySQL.
- Communicate with the backend through REST APIs.

## 5. AWS Services Used

- Amazon VPC
- Public and Private Subnets
- Availability Zones
- Internet Gateway
- Amazon EC2
- Application Load Balancer (ALB)
- Target Groups
- Security Groups

## 6. Application Flow

1. User accesses the application through the ALB DNS endpoint.
2. ALB forwards the request to the registered frontend EC2 instance.
3. Nginx serves the frontend application.
4. Frontend sends API requests to Nginx.
5. Nginx reverse proxy forwards API requests to the Spring Boot backend.
6. Backend connects to MySQL database.
7. Employee data is returned to the frontend.

## 7. Project Structure

```text
aws-employee-management-crud/
├── README.md
├── frontend/
├── backend/
├── database/
├── architecture/
├── screenshots/
└── .gitignore
```

## 8. Screenshots

Screenshots of the working application and AWS infrastructure are available in the `screenshots/` directory.

## 9. Deployment Status

- Frontend deployed on an EC2 instance using Nginx.
- Backend deployed on an EC2 instance using Spring Boot.
- MySQL database configured on a separate EC2 instance.
- Application Load Balancer configured with a Target Group.
- CRUD operations tested successfully.

## 10. Future Enhancements

- Configure Auto Scaling for frontend instances.
- Deploy multiple application instances across Availability Zones.
- Configure backend process management using systemd.
- Automate application deployment using CI/CD pipelines.
- Implement infrastructure provisioning using Terraform.
- Improve monitoring and logging.

## 11. Learning Outcomes

Through this project, I gained practical experience in:

- Deploying a multi-tier Java application on AWS.
- Configuring VPC and subnet networking.
- Setting up EC2 instances and Security Groups.
- Configuring Application Load Balancer and Target Groups.
- Configuring Nginx as a reverse proxy.
- Connecting Spring Boot applications with MySQL.
- Troubleshooting application connectivity and deployment issues.
