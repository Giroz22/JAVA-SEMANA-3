DROP DATABASE IF EXISTS db_flights;
CREATE DATABASE IF NOT EXISTS db_flights;

USE db_flights;

CREATE TABLE Plane(
	id INT PRIMARY KEY AUTO_INCREMENT,
	model VARCHAR(4),
    capacity INT
);

CREATE TABLE Flight(
	id INT PRIMARY KEY AUTO_INCREMENT,
	destino VARCHAR(50),
	departure_date DATE,
	departure_time TIME,
    id_plane INT,
    CONSTRAINT FK_flightplane FOREIGN KEY (id_plane)
    REFERENCES Plane(id)
);

CREATE TABLE Passenger(
	id INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(35),
    surname VARCHAR(35),
    identity_card VARCHAR(15)
);

create table Reservation(
	id INT PRIMARY KEY AUTO_INCREMENT,
	id_passenger INT,
    id_flight INT,
	date_reservation DATE,
    seat VARCHAR(5),
	CONSTRAINT fk_reservation_passanger FOREIGN KEY (id_passenger) ON DELETE CASCADE
    REFERENCES Passenger(id),
    CONSTRAINT fk_reservation_flight FOREIGN KEY(id_flight)
    REFERENCES Flight(id)
);

-- Insert sample data into the planes table
INSERT INTO Plane (model, capacity) VALUES
('A320', 180),
('B737', 160),
('A350', 300),
('B777', 350),
('E190', 100);

-- Insert sample data into the flights table
INSERT INTO Flight (destino, departure_date, departure_time, id_plane) VALUES
('New York', '2024-04-05', '08:00:00', 1),
('London', '2024-04-06', '10:30:00', 2),
('Paris', '2024-04-07', '11:15:00', 3),
('Tokyo', '2024-04-08', '14:00:00', 4),
('Sydney', '2024-04-09', '15:45:00', 5);

-- Insert sample data into the passengers table
INSERT INTO Passenger(name, surname, identity_card) VALUES
('Alice', 'Johnson', '123456789'),
('Bob', 'Smith', '987654321'),
('Charlie', 'Brown', '456123789'),
('Diana', 'Williams', '789456123'),
('Eva', 'Miller', '321654987');

-- Insert sample data into the reservations table
INSERT INTO Reservation(id_passenger, id_flight, date_reservation, seat) VALUES
(1, 1, '2024-04-01', 'A1'),
(2, 2, '2024-04-02', 'B2'),
(3, 3, '2024-04-03', 'C3'),
(4, 4, '2024-04-04', 'D4'),
(5, 5, '2024-04-05', 'E5');

SELECT * from Reservation;