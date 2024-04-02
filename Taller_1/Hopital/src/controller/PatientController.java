package controller;

import entity.Patient;
import model.BaseModel;
import model.PatientModel;

import javax.swing.*;
import java.time.LocalDate;

public class PatientController extends BaseController{
    public PatientController() {
        super(new PatientModel());
    }

    @Override
    public Object requestData(int id) {
        Patient objPatient = null;

        try {
            objPatient = new Patient(
                    id,
                    JOptionPane.showInputDialog(null, "Write the name of the patient:"),
                    JOptionPane.showInputDialog(null, "Write the surname of the patient:"),
                    LocalDate.parse( JOptionPane.showInputDialog(null,"Enter the value of year") + "-" + JOptionPane.showInputDialog(null,"Enter the value of month") + "-" + JOptionPane.showInputDialog(null, "Enter the value of month")),
                    JOptionPane.showInputDialog(null, "Write the identification document of the patient:")
            );
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error requestData: " + e.getMessage());
        }
        return objPatient;
    }
}
