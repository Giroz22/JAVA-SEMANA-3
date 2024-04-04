package controller;

import entity.Flight;
import entity.Plane;
import model.FlightModel;
import model.PlaneModel;

import javax.swing.*;
import java.sql.Date;
import java.sql.Time;

public class FlightController extends BaseController{
    public FlightController() {
        super(new FlightModel());
    }

    @Override
    public Object requestData(int id) {
        PlaneModel planeModel = new PlaneModel();
        Object[] listPlanes = planeModel.findAll().toArray();

        return new Flight(
                id,
                JOptionPane.showInputDialog(null, "Writes the destiny"),
                Date.valueOf(
                        JOptionPane.showInputDialog(null, "Enter the year of flight") + "-" +
                        JOptionPane.showInputDialog(null, "Enter the month of flight") + "-" +
                        JOptionPane.showInputDialog(null, "Enter the day of flight")
                ),
                Time.valueOf(
                        JOptionPane.showInputDialog(null, "Enter the hour of flight") + ":" +
                        JOptionPane.showInputDialog(null, "Enter the minutes of flight") + ":" +
                        "00"
                ),
                ((Plane)(JOptionPane.showInputDialog(
                        null,
                        "Select the Speciality of the doctor:",
                        "Specialities",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        listPlanes,
                        listPlanes[0]//Terminar...
                ))).getId()
        );
    }
}
