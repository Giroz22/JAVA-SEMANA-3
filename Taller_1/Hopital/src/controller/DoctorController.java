package controller;

import entity.Doctor;
import entity.Speciality;
import model.DoctorModel;
import model.SpecialityModel;

import javax.swing.*;

public class DoctorController extends BaseController{

    public DoctorController() {
        super(new DoctorModel());
    }

    @Override
    public Object requestData(int id) {
        Doctor objDoctor = null;
        try {
            SpecialityModel objSpecialityModel = new SpecialityModel();
            Object[] listSpecialities = objSpecialityModel.findAll().toArray();

            objDoctor = new Doctor(
                    id,
                    JOptionPane.showInputDialog(null, "Write the name of the doctor:"),
                    JOptionPane.showInputDialog(null, "Write the surname of the doctor:"),
                    (Speciality) (JOptionPane.showInputDialog(
                            null,
                            "Select the Speciality of the doctor:",
                            "Specialities",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            listSpecialities,
                            listSpecialities[0]
                    ))
            );
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error requestData: " + e.getMessage());
        }
        return objDoctor;
    }
}
