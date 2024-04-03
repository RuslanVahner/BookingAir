CREATE TABLE IF NOT EXISTS users (
    id BINARY(16) PRIMARY KEY NOT NULL,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    role VARCHAR(20) NOT NULL
    );

CREATE TABLE IF NOT EXISTS flights (
    id BINARY(16) PRIMARY KEY NOT NULL,
    flight_number VARCHAR(255) NOT NULL,
    departure_city VARCHAR(255) NOT NULL,
    arrival_city VARCHAR(255) NOT NULL,
    departure_time TIMESTAMP NOT NULL,
    arrival_time TIMESTAMP NOT NULL,
    status VARCHAR(20) NOT NULL
    );

CREATE TABLE IF NOT EXISTS bookings (
    id BINARY(16) PRIMARY KEY NOT NULL,
    user_id BINARY(16) NOT NULL,
    flight_id BINARY(16) NOT NULL,
    booking_date TIMESTAMP NOT NULL,
    status VARCHAR(20) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (flight_id) REFERENCES flights(id)
    );

CREATE TABLE IF NOT EXISTS reviews (
    id BINARY(16) PRIMARY KEY NOT NULL,
    user_id BINARY(16) NOT NULL,
    flight_id BINARY(16) NOT NULL,
    review_text TEXT NOT NULL,
    rating INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (flight_id) REFERENCES flights(id)
    );
