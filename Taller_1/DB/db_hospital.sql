DROP DATABASE IF EXISTS db_hospital;
CREATE DATABASE IF NOT EXISTS db_hospital;

USE db_hospital;

CREATE TABLE specialities(
	id_speciality INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(35),
	description VARCHAR(200)
);

CREATE TABLE doctors(
	id_doctor INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(35),
	surname VARCHAR(35),
    id_speciality INT,
    #Relation
	CONSTRAINT FK_DoctorEspeciality FOREIGN KEY (id_speciality) 
    REFERENCES specialities(id_speciality)
);

CREATE TABLE patients(
	id_patient INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(35),
	surname VARCHAR(35),
	birthdate DATE,
	identification_document VARCHAR(15)
);

CREATE TABLE appointments(
	id_appointment INT PRIMARY KEY AUTO_INCREMENT,
	id_patient INT,
	id_doctor INT,
	date_appointment DATE,
	time_appointment TIME,
	motive VARCHAR(200),
    CONSTRAINT FK_appointmentPatient FOREIGN KEY (id_patient)
    REFERENCES patients(id_patient),
    CONSTRAINT FK_appointmentDoctor FOREIGN KEY (id_doctor)
    REFERENCES doctors(id_doctor)
);

-- Insert sample data into the specialities table
INSERT INTO specialities (name, description) VALUES
('Cardiology', 'Specializing in heart-related issues'),
('Dermatology', 'Specializing in skin-related issues'),
('Neurology', 'Specializing in nervous system disorders'),
('Orthopedics', 'Specializing in bone and joint issues'),
('Ophthalmology', 'Specializing in eye-related issues'),
('Pediatrics', 'Specializing in children''s health'),
('Psychiatry', 'Specializing in mental health'),
('Urology', 'Specializing in urinary tract issues'),
('Gynecology', 'Specializing in women''s reproductive health'),
('Endocrinology', 'Specializing in hormonal disorders');

-- Insert sample data into the doctors table
INSERT INTO doctors (name, surname, id_speciality) VALUES
('John', 'Doe', 1),
('Jane', 'Smith', 2),
('Michael', 'Johnson', 3),
('Emily', 'Williams', 4),
('David', 'Brown', 5),
('Jennifer', 'Jones', 6),
('Christopher', 'Davis', 7),
('Jessica', 'Miller', 8),
('Daniel', 'Wilson', 9),
('Sarah', 'Martinez', 10);

-- Insert sample data into the patients table
INSERT INTO patients (name, surname, birthdate, identification_document) VALUES
('Alice', 'Johnson', '1990-05-15', '123456789'),
('Bob', 'Smith', '1985-08-20', '987654321'),
('Charlie', 'Brown', '1978-03-10', '456123789'),
('Diana', 'Williams', '2000-11-25', '789456123'),
('Eva', 'Miller', '1995-07-08', '321654987'),
('Frank', 'Jones', '1982-09-30', '654789321'),
('Grace', 'Davis', '1970-12-05', '147258369'),
('Harry', 'Wilson', '1998-02-18', '852963147'),
('Ivy', 'Martinez', '1989-06-22', '369147852'),
('Jack', 'Anderson', '1975-04-12', '963852741');

-- Insert sample data into the appointments table
INSERT INTO appointments (id_patient, id_doctor, date_appointment, time_appointment, motive) VALUES
(1, 1, '2024-04-05', '09:00:00', 'Routine check-up'),
(2, 2, '2024-04-06', '10:30:00', 'Skin rash'),
(3, 3, '2024-04-07', '11:15:00', 'Headache'),
(4, 4, '2024-04-08', '14:00:00', 'Fractured arm'),
(5, 5, '2024-04-09', '15:45:00', 'Eye examination'),
(6, 6, '2024-04-10', '08:30:00', 'Childhood vaccinations'),
(7, 7, '2024-04-11', '09:45:00', 'Depression'),
(8, 8, '2024-04-12', '12:00:00', 'Urinary tract infection'),
(9, 9, '2024-04-13', '13:30:00', 'Menstrual irregularities'),
(10, 10, '2024-04-14', '16:15:00', 'Diabetes management');

