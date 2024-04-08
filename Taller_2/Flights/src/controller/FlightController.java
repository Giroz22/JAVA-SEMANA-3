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
import java.util.List;

public class FlightController extends BaseController<Flight, FlightModel>{
    public FlightController() {
        super(new FlightModel());
    }

    @Override
    public Flight requestData(int id) {
        Flight objFli = null;
        try {
            PlaneModel planeModel = new PlaneModel();
            Object[] listPlanes = planeModel.findAll().toArray();

             objFli = new Flight(
                    id,
                    JOptionPane.showInputDialog(null, "Writes the destiny"),
                    Date.valueOf(
                            JOptionPane.showInputDialog(null, "Enter the Date of flight (yyyy-mm-dd)")
                    ),
                    Time.valueOf(
                            JOptionPane.showInputDialog(null, "Enter the time of flight (hh:mm)" + ":" + "00")
                    ),
                    ((Plane) (JOptionPane.showInputDialog(
                            null,
                            "Select the plane of the flight:",
                            "Planes",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            listPlanes,
                            listPlanes[0]
                    ))).getId()
            );
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Tipo de dato no valido");
        }
        return objFli;
    }

    @Override
    public Flight requestData(int id, Flight objFli) {
        Flight newObjFli = null;
        try {

            PlaneModel planeModel = new PlaneModel();

            List<Plane> listPlanes = planeModel.findAll();

            newObjFli = new Flight(
                id,
                JOptionPane.showInputDialog(null, "Writes the destiny", objFli.getDestino()),
                Date.valueOf(
                        JOptionPane.showInputDialog(null, "Enter the Date of flight (yyyy-mm-dd)", objFli.getDeparture_date())
                ),
                Time.valueOf(
                        JOptionPane.showInputDialog(null, "Enter the time of flight (hh:mm)", objFli.getDeparture_time())
                ),
                ((Plane)(JOptionPane.showInputDialog(
                        null,
                        "Select the plane of the flight:",
                        "Planes",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        listPlanes.toArray(new Plane[0]),
                        planeModel.findAll().stream().filter(p-> p.getId() == objFli.getId_plane()).toArray()[0]
                ))).getId()
            );
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Tipo de dato no valido");
        }
        return objFli;
    }
}
