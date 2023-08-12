# Credit Card Transactions API
This project involves a Spring Boot application for managing credit card transactions through a REST API. The application allows listing, filtering, and sorting credit card transactions using various features.

## Getting Started

### Clone the repo

```shell
git clone https://github.com/ochisalim/credit-card-transactions
cd credit-card-transactions
```

## Features

- **List Transactions**: The API provides an endpoint to retrieve the list of credit card transactions.
- **Filtering**: Transactions can be filtered by amount, merchant, and status (approved, refused, pending).
- **Sorting**: Transactions can be sorted by amount, merchant, or status.
- **Pagination**: Transactions can be paginated by `page` and `size` query parameters through the transaction list.

## Data Source

- Transactions data is fetched from a JSON file named `transactionsMock.json`.

## Technologies Used

- Java 11
- Spring Boot 2.7.14
- Spring Data (Mock Repository)
- Maven

## Installation & Run

1. Clone this repository to your local machine.
2. Open the project in your preferred IDE (e.g. IntelliJ IDEA Community Edition).
3. Run the application using your IDE.
4. Alternatively, you can build the project using Maven and generate an executable JAR file. In your terminal, execute the following command:

```shell
mvn clean install
```
The build artifacts will be stored in the target/ directory. You can run the JAR file with:

```shell
java -jar target/credit-card-transactions-0.0.1-SNAPSHOT.jar
```
Shut it down manually with `Ctrl-C`.

## Usage

Once the application is running, you can access the API through the following endpoints (To interact with the API, you can use tools like Postman):

- `GET /v1/api/transactions`: Get the list of all transactions.
- `GET /v1/api/transactions/filter`: Filter transactions by amount, merchant, and status 
- `GET /v1/api/transactions/sort-page`: Sort and paginate transactions by page and size (ascending by default).
- `GET /v1/api/transactions/sort-property`: Sort transactions by transaction property (amount, merchant, status) (default sorting by amount) (ascending by default).

## Example Usage

### Get All Transactions

Send a GET request to `http://localhost:8080/v1/api/transactions` to retrieve a list of all transactions.

### Filter Transactions

Send a GET request to `http://localhost:8080/v1/api/transactions/filter` with query parameters for filtering:

- `amount`: Filter by transaction amount.
- `merchant`: Filter by merchant name.
- `status`: Filter by transaction status (approved, refused, pending).

For example:
```shell
http://localhost:8080/v1/api/transactions/filter?amount=100.0&merchant=Amazon&status=approved
```

### Sort Transactions

Send a GET request to `http://localhost:8080/v1/api/transactions/sort-page` with query parameters for sorting pages :

- `sortDirection`: Sorting direction (asc or desc).
- `page`: Pagination page.
- `size`: Number of transactions per page.

For example:
```shell
http://localhost:8080/v1/api/transactions/sort-page?sortDirection=desc&page=1&size=5
```

### Sort Transactions by Property

Send a GET request to `http://localhost:8080/v1/api/transactions/sort-property` with query parameters for sorting:

- `sortProperty`: Property to sort by (amount, merchant, status).
- `sortDirection`: Sorting direction (asc or desc).

For example:
```shell
http://localhost:8080/v1/api/transactions/sort-property?sortProperty=status&sortDirection=asc
```

## Testing

The application comes with unit tests for the controller. To run the tests, use your IDE or run `mvn test` in the terminal.
