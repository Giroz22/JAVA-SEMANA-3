package model;

import config.ConfigDB;
import entity.Patient;

import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;

public class PatientModel extends BaseModel{
    public PatientModel() {
        super("patients");
    }

    @Override
    public Object getInfoObject(ResultSet objResult) {
        Object objPatient = null;
        try{
            int id_patient = objResult.getInt(1);
            String name = objResult.getString(2);
            String surname = objResult.getString(3);
            LocalDate birthdate = LocalDate.parse(objResult.getString(4));
            String identification_document = objResult.getString(5);

            objPatient = new Patient(id_patient,name,surname,birthdate,identification_document);
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error get info patient: " + e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
        return objPatient;
    }

    @Override
    public PreparedStatement setInfoSave(Object obj) {
        PreparedStatement objPreparedStatement = null;
        try {
            //Preparamos el PreparedStatement
            String sql = "INSERT INTO patients(id_patient, name, surname,birthdate,identification_document) VALUES (?,?,?,?,?);";

            objPreparedStatement = ConfigDB.objConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //Asignamos los valores que se van a enviar
            Patient objPatient = (Patient) obj;
            objPreparedStatement.setInt(1,objPatient.getId_patient());
            objPreparedStatement.setString(2,objPatient.getName());
            objPreparedStatement.setString(3,objPatient.getSurname());
            objPreparedStatement.setDate(4, Date.valueOf(objPatient.getBirthdate()));
            objPreparedStatement.setString(5,objPatient.getIdentification_document());

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error set info save patient: " + e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
        return objPreparedStatement;
    }

    @Override
    public PreparedStatement setInfoUpdate(int id, Object obj) {
        PreparedStatement objPreparedStatement = null;
        try{
            //Preparamos el PreparedStatement
            String sql = "UPDATE patients SET name=? surname=? birthdate=? identification_document=? WHERE id=?";
            objPreparedStatement = ConfigDB.objConnection.prepareStatement(sql);

            //Asignamos los valores que serán actualizados
            Patient objPatient  = (Patient) obj;
            objPreparedStatement.setString(1, objPatient.getName());
            objPreparedStatement.setString(2, objPatient.getSurname());
            objPreparedStatement.setDate(3, Date.valueOf(objPatient.getBirthdate()));
            objPreparedStatement.setString(4, objPatient.getIdentification_document());
            objPreparedStatement.setInt(5, id);

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error set info update patient: " + e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
        return objPreparedStatement;
    }
}
