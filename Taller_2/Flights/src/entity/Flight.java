package entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Flight {
    private int id;
    private String destino;
    private LocalDate departure_date;
    private LocalTime departure_time;
    private Plane plane;

    public Flight() {
    }

    public Flight(int id, String destino, LocalDate departure_date, LocalTime departure_time, Plane plane) {
        this.id = id;
        this.destino = destino;
        this.departure_date = departure_date;
        this.departure_time = departure_time;
        this.plane = plane;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public LocalDate getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(LocalDate departure_date) {
        this.departure_date = departure_date;
    }

    public LocalTime getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(LocalTime departure_time) {
        this.departure_time = departure_time;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", destino='" + destino + '\'' +
                ", departure_date=" + departure_date +
                ", departure_time=" + departure_time +
                ", plane=" + plane +
                '}';
    }
}
