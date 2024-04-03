package entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {
    //Attributes
    private int id_appointment;
    private Patient objPatient;
    private Doctor objDoctor;
    private LocalDate date_appointment;
    private LocalTime time_appointment;
    private String motive;

    //Constructors
    public Appointment() {
    }

    public Appointment(int id_appointment, Patient objPatient, Doctor objDoctor, LocalDate date_appointment, LocalTime time_appointment, String motive) {
        this.id_appointment = id_appointment;
        this.objPatient = objPatient;
        this.objDoctor = objDoctor;
        this.date_appointment = date_appointment;
        this.time_appointment = time_appointment;
        this.motive = motive;
    }

    //Methods
    //Setter and Getters
    public int getId() {
        return id_appointment;
    }

    public void setId(int id_appointment) {
        this.id_appointment = id_appointment;
    }

    public Patient getObjPatient() {
        return objPatient;
    }

    public void setObjPatient(Patient objPatient) {
        this.objPatient = objPatient;
    }

    public Doctor getObjDoctor() {
        return objDoctor;
    }

    public void setObjDoctor(Doctor objDoctor) {
        this.objDoctor = objDoctor;
    }

    public LocalDate getDate_appointment() {
        return date_appointment;
    }

    public void setDate_appointment(LocalDate date_appointment) {
        this.date_appointment = date_appointment;
    }

    public LocalTime getTime_appointment() {
        return time_appointment;
    }

    public void setTime_appointment(LocalTime time_appointment) {
        this.time_appointment = time_appointment;
    }

    public String getMotive() {
        return motive;
    }

    public void setMotive(String motive) {
        this.motive = motive;
    }

    //ToString
    @Override
    public String toString() {
        return "Appointments{" +
                "\nPatient=" + objPatient.getName() + objPatient.getSurname() +
                "\nDoctor=" + objDoctor.getName() + objDoctor.getSurname() +
                "\nDate=" + date_appointment +
                "\nTime" + time_appointment +
                "\nMotive='" + motive + '\'' +
                '}';
    }
}
