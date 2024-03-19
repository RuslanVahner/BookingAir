CREATE TABLE IF NOT EXISTS Account (
    id BINARY(36) PRIMARY KEY NOT NULL,
    login VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    balance DECIMAL NOT NULL,
    сreate_account_date TIMESTAMP NOT NULL,
    owner VARCHAR(255) NOT NULL,
    account_status ENUM('ACTIVE', 'BLOCKED', 'REMOVED') NOT NULL,
    role ENUM('ADMIN','PASSENGER') NOT NULL
    );


CREATE TABLE IF NOT EXISTS Cart (
    id BINARY(36) PRIMARY KEY NOT NULL,
    account_id BINARY(36)
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

CREATE TABLE IF NOT EXISTS Flight(
    id BINARY(36) PRIMARY KEY NOT NULL,
    name_flight VARCHAR(255) NOT NULL,
    flight_number INT NOT NULL,
    departure_airport VARCHAR(255),
    arrival_airport VARCHAR(255),
    departure_date TIMESTAMP NOT NULL,
    arrival_date TIMESTAMP NOT NULL,
    flight_time TIME NOT NULL,
    airline VARTCHAR(255),
    trips_status ENUM('ACTIVE', 'CANCELLED', 'TRANSFERRED') NOT NULL
    );


CREATE TABLE IF NOT EXISTS Reservations (
    id BINARY(36) PRIMARY KEY NOT NULL,
    reservations_reference VARCHAR(255) NOT NULL,
    reservations_date TIMESTAMP(255) NOT NULL
    );


CREATE TABLE IF NOT EXISTS Ticket (
    id BINARY(36) PRIMARY KEY NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    purchase_time TIMESTAMP NOT NULL,
    ticket_number INT NOT NULL,
    ticket_class ENUM('ECONOMY', 'BUSINESS') NOT NULL,
    type ENUM('ADULT', 'CHILD') NOT NULL,
    ticket_status ENUM('RESERVED', 'PAID', 'CANCELLED') NOT NULL,
    flight_id BINARY(36),
    account_id BINARY(36),
    reservation_id BINARY(36),
    passenger_id BINARY(36),
    cart_id BINARY(36)

    );