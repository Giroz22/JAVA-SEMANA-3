package entity;

import java.sql.Time;
import java.sql.Date;

public class Flight {
    private int id;
    private String destino;
    private Date departure_date;
    private Time departure_time;
    private int id_plane;

    public Flight() {
    }

    public Flight(int id, String destino, Date departure_date, Time departure_time, int id_plane) {
        this.id = id;
        this.destino = destino;
        this.departure_date = departure_date;
        this.departure_time = departure_time;
        this.id_plane = id_plane;
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

    public Date getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(Date departure_date) {
        this.departure_date = departure_date;
    }

    public Time getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(Time departure_time) {
        this.departure_time = departure_time;
    }

    public int getId_plane() {
        return id_plane;
    }

    public void setId_plane(int id_plane) {
        this.id_plane = id_plane;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", destino='" + destino + '\'' +
                ", departure_date=" + departure_date +
                ", departure_time=" + departure_time +
                ", id_plane=" + id_plane +
                '}';
    }
}
