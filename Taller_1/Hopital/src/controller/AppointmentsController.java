package controller;

import entity.Appointment;
import entity.Doctor;
import entity.Patient;
import model.AppointmentsModel;
import model.DoctorModel;
import model.PatientModel;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class AppointmentsController extends BaseController{
    public AppointmentsController() {
        super(new AppointmentsModel());
    }

    @Override
    public Object requestData(int id) {
        Appointment objAppointment = null;

        try{
            //Buscamos la lista de todos los pacientes
            PatientModel objPatient = new PatientModel();
            Object[] listPatients = objPatient.findAll().toArray();

            //Buscamos la lista de todos los doctores
            DoctorModel objDoctorModel = new DoctorModel();
            Object[] listDoctors = objDoctorModel.findAll().toArray();

            objAppointment  =  new Appointment(
                    id,
                    (Patient)(JOptionPane.showInputDialog(
                            null,
                            "Select the patient:",
                            "Specialities",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            listPatients,
                            listPatients[0]
                    )),
                    (Doctor)(JOptionPane.showInputDialog(
                            null,
                            "Select the doctor:",
                            "Specialities",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            listDoctors,
                            listDoctors[0]
                    )),
                    LocalDate.parse( JOptionPane.showInputDialog(null,"Enter the year of the Appointment") + "-" + JOptionPane.showInputDialog(null,"Enter the month of the Appointment") + "-" + JOptionPane.showInputDialog(null, "Enter the month of the Appointment")),
                    LocalTime.parse( JOptionPane.showInputDialog(null,"Enter the hour of the Appointment") + ":" + "00" + ":" + "00"),
                    JOptionPane.showInputDialog(null,"Write the motive of the Appointment")
            );
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error requestData: " + e.getMessage());
        }
        return objAppointment;
    }
}
