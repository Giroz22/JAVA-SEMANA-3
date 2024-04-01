package entity;

import java.util.Date;

public class Patient {
    //Attributes
    private int id_patient;
    private String name;
    private String surname;
    private Date birthdate;
    private String identification_document;

    //Constructors
    public Patient() {
    }

    public Patient(int id_patient, String name, String surname, Date birthdate, String identification_document) {
        this.id_patient = id_patient;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.identification_document = identification_document;
    }

    //Methods
    //Setter and Getters
    public int getId_patient() {
        return id_patient;
    }

    public void setId_patient(int id_patient) {
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

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
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
                "id_patient=" + id_patient +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthdate=" + birthdate +
                ", identification_document='" + identification_document + '\'' +
                '}';
    }
}
