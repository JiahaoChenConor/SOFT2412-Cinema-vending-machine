CREATE DATABASE IF NOT EXISTS CINEMA;

USE CINEMA;

-- Casts(cast_id, cast_name, film_id)
--
-- Film (film_id, classification, synopsis, release date, director)
-- // ‘cast’ can be shown by joining Cast
-- // ‘upcoming times’ and ‘screen’ can be shown by joining Cinema_Film
--
-- Cinema_Film(film_id, cinema_id, timestamp, screen, price)
--
-- Cinema(cinema_id, location)

# CREATE TABLE IF NOT EXISTS Cast(
#     cast_id INT PRIMARY KEY,
#     cast_name VARCHAR(16) NOT NULL,
#     film_id INT
# );

CREATE TABLE IF NOT EXISTS Casts(
    cast_id INT PRIMARY KEY ,
    cast_name VARCHAR(32) NOT NULL,
    film_id INT
);


CREATE TABLE IF NOT EXISTS Film(
    film_id INT PRIMARY KEY,
    film_name VARCHAR(38) NOT NULL ,
    classification VARCHAR(16) NOT NULL,
    synopsis VARCHAR(500) NOT NULL ,
    release_date DATE NOT NULL,
    director VARCHAR(32) NOT NULL
);

CREATE TABLE IF NOT EXISTS Cinema_Film(
    show_id INT PRIMARY KEY,
    film_id INT,
    cinema_id INT ,
    timestamp_film TIMESTAMP ,
    screen VARCHAR(10),
    price DECIMAL NOT NULL,
    num_seat_rows INT,
    num_seat_columns INT
);

CREATE TABLE IF NOT EXISTS Ticket(
    ticket_id INT PRIMARY KEY ,
    show_id INT,
    type_person VARCHAR(30),
    seat_row INT,
    seat_column INT,
    status VARCHAR(10) DEFAULT 'purchased',
    username VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Cinema(
    cinema_id INT PRIMARY KEY ,
    location VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS User(
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL DEFAULT 'CUSTOMER'
);

CREATE TABLE IF NOT EXISTS CancelledTransaction(
    transaction_id INT PRIMARY KEY ,
    user VARCHAR(255) DEFAULT 'ANONYMOUS',
    reason VARCHAR(255),
    timestamp_cancelled TIMESTAMP
);

CREATE TABLE IF NOT EXISTS GiftCard(
    card_number CHAR(16) NOT NULL,
    PIN         CHAR(4) NOT NULL,
    balance     DECIMAL(6,2)  NOT NULL,
    is_lost     BOOLEAN DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS CreditCard (
    cardholder_name VARCHAR(255) NOT NULL,
    credit_card_number VARCHAR(255) NOT NULL,
    username VARCHAR(255),
    PRIMARY KEY (cardholder_name, credit_card_number, username)
)