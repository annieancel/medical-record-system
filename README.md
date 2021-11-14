# Medical Record System
Medical Record System is used to hold the medical records.It supports uploading
a CSV file and converting and saving the records in to the system.It also provides helpful
Apis to fetch and delete the saved medical records
## Features
1. Upload a CSV File and save the records
2. Get all saved records
3. Get record by code
4. Delete all records.

## Technologies
- Java 11
- Spring Boot
- Spring Data JPA
- H2 DB
- Mapstruct
- Lombok
- OpenAPI Docs
- OpenCSV
- Docker

### Prerequisites
You need the following installed in your system:
- Java 11
- Maven
- Docker (If you are running application using first method)

### Installation
**Navigate to the root of the project**

1. To run with docker
```bash
$ mvn spring-boot:build-image
```
```bash
$ docker run -it -p8080:8080 patient-record-system:0.0.1-SNAPSHOT
```

2. To run as plain spring boot application
```bash
$ mvn spring-boot:run
```

### Usage
you can find the latest documentation how to use
by visiting http://localhost:8080/swagger-ui.html
> *Note:* that there is no UI for this application; it only exposes REST endpoints

### Running tests
To run all the unit tests
```bash
$ mvn clean test
```