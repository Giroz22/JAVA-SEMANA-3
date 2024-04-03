package entity;

import java.time.LocalDate;

public class Reservation {
    private String id;
    private Passenger passenger;
    private Flight flight;
    private LocalDate date_reservation;
    private String seat;

    public Reservation() {
    }

    public Reservation(String id, Passenger passenger, Flight flight, LocalDate date_reservation, String seat) {
        this.id = id;
        this.passenger = passenger;
        this.flight = flight;
        this.date_reservation = date_reservation;
        this.seat = seat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public LocalDate getDate_reservation() {
        return date_reservation;
    }

    public void setDate_reservation(LocalDate date_reservation) {
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
                ", passenger=" + passenger +
                ", flight=" + flight +
                ", date_reservation=" + date_reservation +
                ", seat='" + seat + '\'' +
                '}';
    }
}
