# credit-card-transactions
This project involves a Spring Boot application for managing credit card transactions through a REST API. The application allows listing, filtering, and sorting credit card transactions using various features.

## Get started

### Clone the repo

```shell
git clone https://github.com/ochisalim/credit-card-transactions
cd credit-card-transactions
```

## Features

- **List Transactions**: The API provides an endpoint to retrieve the list of credit card transactions.
- **Filtering**: Transactions can be filtered by amount, merchant, and status (approved, refused, pending).
- **Sorting**: Transactions can be sorted by amount, merchant, or status.
- **Pagination**: Pagination is implemented using the `page` and `size` query parameters.

## Data Source

- Transactions data is fetched from a JSON file named `transactionsMock.json`.

## Technologies Used

- Java 11
- Spring Boot 2.7.14
- Spring Data (Mock Repository)
- Maven

## Installation

1. Clone this repository to your local machine.
2. Open the project in your favorite IDE (I used IntelliJ IDEA Community).
3. Run the application using your IDE.

### Development server

To build the project using Maven and generate the executable JAR file, run the following command:

```shell
mvn clean install
```

The build artifacts will be stored in the target/ directory. You can run the JAR using the following command:

```shell
java -jar target/enretien-back-0.0.1-SNAPSHOT.jar
```
Shut it down manually with `Ctrl-C`.

## Usage

Once the application is running, you can access the API through the following endpoints (e.g. Postman):

- `GET /v1/api/transactions`: Get the list of all transactions.
- `GET /v1/api/transactions/filter`: Filter transactions by amount, merchant, and status.
- `GET /v1/api/transactions/sort`: Sort transactions by amount (ascending by default).
- `GET /v1/api/transactions/sort-property`: Sort transactions by transaction property (amount, merchant, status).

## Testing

The application comes with unit tests for the controller. To run the tests, use your IDE or run `mvn test` in the terminal.
