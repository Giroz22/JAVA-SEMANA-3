package controller;

import entity.Plane;
import model.PlaneModel;

import javax.swing.*;

public class PlaneController extends BaseController{
    public PlaneController() {
        super(new PlaneModel());
    }

    @Override
    public Object requestData(int id) {

        return new Plane(
                id,
                JOptionPane.showInputDialog(null, "Write the model of plane:"),
                Integer.parseInt(JOptionPane.showInputDialog(null, "Write the capacity of plane"))
                );
    }
}
