package controller;

import entity.Flight;
import entity.Passenger;
import entity.Reservation;
import model.FlightModel;
import model.PassengerModel;
import model.ReservationModel;

import javax.swing.*;
import java.sql.Date;

public class ReservationController extends BaseController{
    public ReservationController() {
        super(new ReservationModel());
    }

    @Override
    public Object requestData(int id) {
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
}
