<p align="center"><img width=100% src="src/main/resources/Banner/banner.png"></p>

[//]: # (&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;)
![Version](https://img.shields.io/badge/version-v0.0.1-blue)
[![Build Status](https://travis-ci.org/anfederico/clairvoyant.svg?branch=master)](https://travis-ci.org/anfederico/clairvoyant)
![Contributions welcome](https://img.shields.io/badge/contributions-welcome-orange.svg)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](https://opensource.org/licenses/MIT)

## Basic Overview

Backend API based on Spring Boot and related Spring frameworks for managing hotel reservations.

<br>

## Tech Stack
- Spring Boot
- Spring Data JPA
- Spring Security
- PostgreSQL
- Rest Exception Handling
- Swagger Documentation

## Dependencies
You can fine all dependencies in `pom.xml` file

## How to use
- Clone the repo.
- Run using IntelliJ IDEA
- Connect Datasource to your Postgres database by modifying application.properties file.
- For now, you can run Swagger GUI in browser by accessing the following link:
```text
http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/
```

### Bug Reports & Feature Requests

Please use the [issue tracker](https://github.com/MostafaAbdelkarim/Hotel-Reservation-System/issues) to report any bugs or file feature requests.

### Pending Features
- Applying appropriate UI 
- JWT Implementation
- Specific Rest Exception Handlers
- More functionalities