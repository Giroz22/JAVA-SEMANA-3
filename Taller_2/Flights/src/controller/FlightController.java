package controller;

import entity.Flight;
import model.BaseModel;
import model.FlightModel;

import javax.swing.*;
import java.sql.Date;
import java.sql.Time;

public class FlightController extends BaseController{
    public FlightController() {
        super(new FlightModel());
    }

    @Override
    public Object requestData(int id) {
        return new Flight(
                id,
                JOptionPane.showInputDialog(null, "Writes the destiny"),
                Date.valueOf(
                        JOptionPane.showInputDialog(null, "Enter the year of flight") + "-" +
                        JOptionPane.showInputDialog(null, "Enter the month of flight") + "-" +
                        JOptionPane.showInputDialog(null, "Enter the day of flight")
                ),
                Time.valueOf(
                        JOptionPane.showInputDialog(null, "Enter the year of flight") + ":" +
                        JOptionPane.showInputDialog(null, "Enter the month of flight") + ":" +
                        "00"
                ),
                (JOptionPane.showInputDialog(
                        null,
                        "Select the Speciality of the doctor:",
                        "Specialities",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        listSpecialities,
                        listSpecialities[0]//Terminar...
                ))
    }
}
