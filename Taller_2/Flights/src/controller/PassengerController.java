package controller;

import entity.Passenger;
import model.BaseModel;
import model.PassengerModel;

import javax.swing.*;

public class PassengerController extends BaseController{
    public PassengerController() {
        super(new PassengerModel());
    }

    @Override
    public Object requestData(int id) {
        return new Passenger(id,
                JOptionPane.showInputDialog("Write the name of passenger: "),
                JOptionPane.showInputDialog("Write the surname of passenger: "),
                JOptionPane.showInputDialog("Write the identity card of passenger: "));
    }
}
