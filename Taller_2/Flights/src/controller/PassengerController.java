package controller;

import entity.Passenger;
import model.BaseModel;
import model.PassengerModel;

import javax.swing.*;

public class PassengerController extends BaseController<Passenger, PassengerModel>{

    public PassengerController() {
        super(new PassengerModel());
    }

    @Override
    public Passenger requestData(int id) {
        return new Passenger(id,
                JOptionPane.showInputDialog("Write the name of passenger: "),
                JOptionPane.showInputDialog("Write the surname of passenger: "),
                JOptionPane.showInputDialog("Write the identity card of passenger: "));
    }

    @Override
    public Passenger requestData(int id, Passenger objPassenger) {
        return new Passenger(id,
                JOptionPane.showInputDialog("Write the name of passenger: ", objPassenger.getName()),
                JOptionPane.showInputDialog("Write the surname of passenger: ", objPassenger.getSurname()),
                JOptionPane.showInputDialog("Write the identity card of passenger: ", objPassenger.getIdentity_card()));
    }
}
