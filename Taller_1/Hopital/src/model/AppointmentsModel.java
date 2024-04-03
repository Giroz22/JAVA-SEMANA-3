package model;

import config.ConfigDB;
import entity.Appointment;
import entity.Doctor;
import entity.Patient;

import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentsModel extends BaseModel{
    public AppointmentsModel() {
        super("appointments");
    }

    @Override
    public Object getInfoObject(ResultSet objResult) {
        Object objAppointments  = null;
        try{
            //Obtenemos toda la info
            int id_appointment = objResult.getInt(1);
            int id_patient = objResult.getInt(2);
            int id_doctor = objResult.getInt(3);
            LocalDate date_appointment = objResult.getDate(4).toLocalDate() ;
            LocalTime time_appointment = objResult.getTime(5).toLocalTime();
            String motive = objResult.getString(6);

            //Buscamos por id al paciente y al doctor
            PatientModel objPatientModel = new PatientModel();
            Patient objPatient = (Patient) objPatientModel.findById(id_patient);

            DoctorModel objDoctorModel = new DoctorModel();
            Doctor objDoctor = (Doctor) objDoctorModel.findById(id_doctor);

            //Asignamos los valores obtenidos a un nuevo objeto Appointments
            objAppointments = new Appointment(id_appointment,objPatient,objDoctor,date_appointment,time_appointment,motive);
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error get info Appointments: " + e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
        return objAppointments;
    }

    @Override
    public PreparedStatement setInfoSave(Object obj) {
        PreparedStatement objPreparedStatement = null;
        try {
            //Preparamos el PreparedStatement
            String sql = "INSERT INTO appointments(id_patient, id_doctor, date_appointment, time_appointment, motive) VALUES (?,?,?,?,?);";
            objPreparedStatement = ConfigDB.objConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //Asignamos los valores que se van a enviar
            Appointment objAppointment = (Appointment) obj;
            objPreparedStatement.setInt(1, objAppointment.getObjPatient().getId());
            objPreparedStatement.setInt(2, objAppointment.getObjDoctor().getId());
            objPreparedStatement.setDate(3, Date.valueOf(objAppointment.getDate_appointment()));
            objPreparedStatement.setTime(4, Time.valueOf(objAppointment.getTime_appointment()));
            objPreparedStatement.setString(5, objAppointment.getMotive());

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error set info save Appointments: " + e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
        return objPreparedStatement;
    }

    @Override
    public PreparedStatement setInfoUpdate(Object obj) {
        PreparedStatement objPreparedStatement = null;
        try{
            //Preparamos el PreparedStatement
            String sql = "UPDATE specialities SET id_patient=?, id_doctor=?, date_appointment=?, time_appointment=?, motive=? WHERE id=?";
            objPreparedStatement = ConfigDB.objConnection.prepareStatement(sql);

            //Asignamos los valores que serán actualizados
            Appointment objAppointment = (Appointment) obj;
            objPreparedStatement.setInt(1, objAppointment.getObjPatient().getId());
            objPreparedStatement.setInt(2, objAppointment.getObjDoctor().getId());
            objPreparedStatement.setDate(3, Date.valueOf(objAppointment.getDate_appointment()));
            objPreparedStatement.setTime(4, Time.valueOf(objAppointment.getTime_appointment()));
            objPreparedStatement.setString(5, objAppointment.getMotive());
            objPreparedStatement.setInt(6, objAppointment.getId());

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error set info update Appointments: " + e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
        return objPreparedStatement;
    }
}
