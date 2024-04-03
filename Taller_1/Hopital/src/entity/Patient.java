package entity;

import java.time.LocalDate;
import java.util.Date;

public class Patient {
    //Attributes
    private int id_patient;
    private String name;
    private String surname;
    private LocalDate birthdate;
    private String identification_document;

    //Constructors
    public Patient() {
    }

    public Patient(int id_patient, String name, String surname, LocalDate birthdate, String identification_document) {
        this.id_patient = id_patient;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.identification_document = identification_document;
    }

    //Methods
    //Setter and Getters
    public int getId() {
        return id_patient;
    }

    public void setId(int id_patient) {
        this.id_patient = id_patient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getIdentification_document() {
        return identification_document;
    }

    public void setIdentification_document(String identification_document) {
        this.identification_document = identification_document;
    }

    //ToString
    @Override
    public String toString() {
        return "Patient{" +
                "\nName='" + name + '\'' +
                "\nSurname='" + surname + '\'' +
                "\nBirthdate=" + birthdate +
                "\nIdentification document='" + identification_document + '\'' +
                '}';
    }
}
