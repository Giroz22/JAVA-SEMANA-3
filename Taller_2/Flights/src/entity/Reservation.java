package entity;

import java.sql.Date;

public class Reservation {
    private int id;
    private int id_passenger;
    private int id_flight;
    private Date date_reservation;
    private String seat;

    public Reservation() {
    }

    public Reservation(int id, int id_passenger, int id_flight, Date date_reservation, String seat) {
        this.id = id;
        this.id_passenger = id_passenger;
        this.id_flight = id_flight;
        this.date_reservation = date_reservation;
        this.seat = seat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_passenger() {
        return id_passenger;
    }

    public void setId_passenger(int id_passenger) {
        this.id_passenger = id_passenger;
    }

    public int getId_flight() {
        return id_flight;
    }

    public void setId_flight(int id_flight) {
        this.id_flight = id_flight;
    }

    public Date getDate_reservation() {
        return date_reservation;
    }

    public void setDate_reservation(Date date_reservation) {
        this.date_reservation = date_reservation;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "  id_passenger=" + id_passenger +
                ", id_flight=" + id_flight +
                ", date_reservation=" + date_reservation +
                ", seat='" + seat + '\'' +
                '}';
    }
}
