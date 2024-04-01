package entity;

import java.sql.Time;
import java.util.Date;

public class Appointments {
    //Attributes
    private int id_appointment;
    private int id_patient;
    private int id_doctor;
    private Date date_appointment;
    private Time time_appointment;
    private String motive;

    //Constructors
    public Appointments() {
    }

    public Appointments(int id_appointment, int id_patient, int id_doctor, Date date_appointment, Time time_appointment, String motive) {
        this.id_appointment = id_appointment;
        this.id_patient = id_patient;
        this.id_doctor = id_doctor;
        this.date_appointment = date_appointment;
        this.time_appointment = time_appointment;
        this.motive = motive;
    }

    //Methods
    //Setter and Getters
    public int getId_appointment() {
        return id_appointment;
    }

    public void setId_appointment(int id_appointment) {
        this.id_appointment = id_appointment;
    }

    public int getId_patient() {
        return id_patient;
    }

    public void setId_patient(int id_patient) {
        this.id_patient = id_patient;
    }

    public int getId_doctor() {
        return id_doctor;
    }

    public void setId_doctor(int id_doctor) {
        this.id_doctor = id_doctor;
    }

    public Date getDate_appointment() {
        return date_appointment;
    }

    public void setDate_appointment(Date date_appointment) {
        this.date_appointment = date_appointment;
    }

    public Time getTime_appointment() {
        return time_appointment;
    }

    public void setTime_appointment(Time time_appointment) {
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
                "id_appointment=" + id_appointment +
                ", id_patient=" + id_patient +
                ", id_doctor=" + id_doctor +
                ", date_appointment=" + date_appointment +
                ", time_appointment=" + time_appointment +
                ", motive='" + motive + '\'' +
                '}';
    }
}
