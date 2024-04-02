package controller;

import entity.Speciality;
import model.SpecialityModel;

import javax.swing.*;

public class SpecialityController extends BaseController
{
    public SpecialityController() {
        super(new SpecialityModel());
    }

    @Override
    public Object requestData(int id) {
        return new Speciality(
                id,
                JOptionPane.showInputDialog(null,"Write the name of the speciality"),
                JOptionPane.showInputDialog(null,"Write the description of the speciality")
        );
    }
}
