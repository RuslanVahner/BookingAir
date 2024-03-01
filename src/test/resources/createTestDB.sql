CREATE TABLE IF NOT EXISTS Account (
    id BINARY(36) PRIMARY KEY NOT NULL,
    login VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    balance DECIMAL NOT NULL,
    account_status ENUM('ACTIVE', 'BLOCKED', 'REMOVED') NOT NULL,
    owner VARCHAR(255) NOT NULL
    );


CREATE TABLE IF NOT EXISTS Passenger (
    id BINARY(36) PRIMARY KEY NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    age INT NOT NULL,
    phone VARCHAR(20) NOT NULL,
    email VARCHAR(60),
    account_id BINARY(36)

    );


CREATE TABLE IF NOT EXISTS Cart (
    id BINARY(36) PRIMARY KEY NOT NULL,
    total_cost DECIMAL NOT NULL,
    account_id BINARY(36)
    );


CREATE TABLE IF NOT EXISTS Trips (
    id BINARY(36) PRIMARY KEY NOT NULL,
    name_trips VARCHAR(255) NOT NULL,
    flight_number INT NOT NULL,
    departure_time TIMESTAMP NOT NULL,
    arrival_time TIMESTAMP NOT NULL,
    flight_time TIME NOT NULL,
    trips_status ENUM('ACTIVE', 'CANCELLED', 'TRANSFERRED') NOT NULL
    );


CREATE TABLE IF NOT EXISTS Airline (
    id BINARY(36) PRIMARY KEY NOT NULL,
    airline_name VARCHAR(255) NOT NULL,
    airline_price DECIMAL NOT NULL,
    trips_id BINARY(36)
    );


CREATE TABLE IF NOT EXISTS Airport (
    id BINARY(36) PRIMARY KEY NOT NULL,
    name_airport VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    airline_id BINARY(36)
    );


CREATE TABLE IF NOT EXISTS Ticket (
    id BINARY(36) PRIMARY KEY NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    data TIMESTAMP NOT NULL,
    ticket_number INT NOT NULL,
    account_id BINARY(16) ,
    service ENUM('ECONOMY', 'BUSINESS', 'FIRST') NOT NULL,
    type ENUM('ADULT', 'CHILD') NOT NULL,
    trips_id BINARY(36),
    airport_id BINARY(36)
    );