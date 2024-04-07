package controller;

import entity.Plane;
import model.BaseModel;
import model.PlaneModel;

import javax.swing.*;

public class PlaneController extends BaseController<Plane, PlaneModel>{

    public PlaneController() {
        super(new PlaneModel());
    }

    @Override
    public Plane requestData(int id) {
        return new Plane(
                id,
                JOptionPane.showInputDialog(null, "Write the model of plane:"),
                Integer.parseInt(JOptionPane.showInputDialog(null, "Write the capacity of plane"))
                );
    }

    @Override
    public Plane requestData(int id, Plane objPlane) {
        return new Plane(
                id,
                JOptionPane.showInputDialog(null, "Write the model of plane:", objPlane.getModel()),
                Integer.parseInt(JOptionPane.showInputDialog(null, "Write the capacity of plane", objPlane.getCapacity()))
        );
    }
}
