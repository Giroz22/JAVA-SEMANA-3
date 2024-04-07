package controller;

import entity.Flight;
import entity.Passenger;
import entity.Reservation;
import model.BaseModel;
import model.FlightModel;
import model.PassengerModel;
import model.ReservationModel;

import javax.swing.*;
import java.sql.Date;
import java.util.Arrays;

public class ReservationController extends BaseController<Reservation, ReservationModel>{

    public ReservationController() {
        super(new ReservationModel());
    }

    @Override
    public Reservation requestData(int id) {
        PassengerModel  objPassengerModel = new PassengerModel();
        Object[] listPassenger = objPassengerModel.findAll().toArray();
        FlightModel objFlightModel = new FlightModel();
        Object[] listFlight = objFlightModel.findAll().toArray();
        return new Reservation(
                id,
                ((Passenger)(JOptionPane.showInputDialog(
                        null,
                        "Select the Speciality passenger:",
                        "Specialities",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        listPassenger,
                        listPassenger[0]

                ))).getId(),
                ((Flight)(JOptionPane.showInputDialog(
                        null,
                        "Select the Speciality flight:",
                        "Specialities",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        listFlight,
                        listFlight[0]
                ))).getId(),
                Date.valueOf(
                        JOptionPane.showInputDialog(null, "Enter the year of flight") + "-" +
                                JOptionPane.showInputDialog(null, "Enter the month of flight") + "-" +
                                JOptionPane.showInputDialog(null, "Enter the day of flight")
                ),
                JOptionPane.showInputDialog(null, "Writes the seat")
                );
    }

    @Override
    public Reservation requestData(int id, Reservation objReservation) {
        PassengerModel  objPassengerModel = new PassengerModel();
        FlightModel objFlightModel = new FlightModel();

        Object[] listPassenger = objPassengerModel.findAll().toArray();
        Object[] listFlight =  objFlightModel.findAll().toArray();

        return new Reservation(
                id,
                ((Passenger)(JOptionPane.showInputDialog(
                        null,
                        "Select the Speciality passenger:",
                        "Specialities",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        listPassenger,
                        Arrays.stream((Passenger[])listPassenger).filter(p-> p.getId() == objReservation.getId_passenger())

                ))).getId(),
                ((Flight)(JOptionPane.showInputDialog(
                        null,
                        "Select the Speciality flight:",
                        "Specialities",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        listFlight,
                        Arrays.stream((Flight[])listFlight).filter(f-> f.getId() == objReservation.getId_flight())
                ))).getId(),
                Date.valueOf(
                        JOptionPane.showInputDialog(null, "Enter the Date of reservation (yyyy-mm-dd)", objReservation.getDate_reservation().toString())
                ),
                JOptionPane.showInputDialog(null, "Writes the seat")
        );
    }
}
