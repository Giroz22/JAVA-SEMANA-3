package controller;

import entity.Flight;
import entity.Plane;
import model.BaseModel;
import model.FlightModel;
import model.PlaneModel;

import javax.swing.*;
import java.sql.Date;
import java.sql.Time;
import java.util.Arrays;

public class FlightController extends BaseController<Flight, FlightModel>{
    public FlightController() {
        super(new FlightModel());
    }

    @Override
    public Flight requestData(int id) {
        PlaneModel planeModel = new PlaneModel();
        Object[] listPlanes = planeModel.findAll().toArray();

        return new Flight(
                id,
                JOptionPane.showInputDialog(null, "Writes the destiny"),
                Date.valueOf(
                        JOptionPane.showInputDialog(null, "Enter the Date of flight (yyyy-mm-dd)")
                ),
                Time.valueOf(
                        JOptionPane.showInputDialog(null, "Enter the time of flight (hh-mm)" + ":" + "00")
                ),
                ((Plane)(JOptionPane.showInputDialog(
                        null,
                        "Select the plane of the flight:",
                        "Planes",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        listPlanes,
                        listPlanes[0]
                ))).getId()
        );
    }

    @Override
    public Flight requestData(int id, Flight objFli) {
        PlaneModel planeModel = new PlaneModel();
        Object[] listPlanes = planeModel.findAll().toArray();

        return new Flight(
                id,
                JOptionPane.showInputDialog(null, "Writes the destiny", objFli.getDestino()),
                Date.valueOf(
                        JOptionPane.showInputDialog(null, "Enter the Date of flight (yyyy-mm-dd)", objFli.getDeparture_date())
                ),
                Time.valueOf(
                        JOptionPane.showInputDialog(null, "Enter the time of flight (hh-mm)" + ":" + "00", objFli.getDeparture_time())
                ),
                ((Plane)(JOptionPane.showInputDialog(
                        null,
                        "Select the plane of the flight:",
                        "Planes",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        listPlanes,
                        Arrays.stream((Flight[])listPlanes).filter(plane -> plane.getId_plane() == objFli.getId_plane())
                ))).getId()
        );
    }
}
