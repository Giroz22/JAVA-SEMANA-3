package model;

import config.ConfigDB;
import entity.Doctor;
import entity.Speciality;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DoctorModel extends BaseModel{
    public DoctorModel() {
        super("doctors");
    }

    @Override
    public Object getInfoObject(ResultSet objResult) {
        Object objDoctor = null;
        try{
            //Obtenemos la info basica del doctor
            int id_doctor = objResult.getInt(1);
            String name = objResult.getString(2);
            String surname = objResult.getString(3);

            //En base a el id de la especialidad buscamos el objeto y lo asignamos
            SpecialityModel ocjSpecialityModel = new SpecialityModel();
            Speciality objSpeciality = (Speciality) ocjSpecialityModel.findById(objResult.getInt(4));

            //Asignamos la info
            objDoctor = new Doctor(id_doctor,name,surname,objSpeciality);
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error get info doctor: " + e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
        return objDoctor;
    }

    @Override
    public PreparedStatement setInfoSave(Object obj) {
        PreparedStatement objPreparedStatement = null;
        try {
            //Preparamos el PreparedStatement
            String sql = "INSERT INTO doctors(name, surname, id_speciality) VALUES (?,?,?);";
            objPreparedStatement = ConfigDB.objConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //Asignamos los valores que se van a enviar
            Doctor objDoctor = (Doctor) obj;
            objPreparedStatement.setString(1,objDoctor.getName());
            objPreparedStatement.setString(2,objDoctor.getSurname());
            objPreparedStatement.setInt(3,objDoctor.getObjSpeciality().getId());

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error set info save doctor: " + e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
        return objPreparedStatement;
    }

    @Override
    public PreparedStatement setInfoUpdate(Object obj) {
        PreparedStatement objPreparedStatement = null;
        try{
            //Preparamos el PreparedStatement
            String sql = "UPDATE doctors SET name=?, surname=?, id_speciality=? WHERE id=?";
            objPreparedStatement = ConfigDB.objConnection.prepareStatement(sql);

            //Asignamos los valores que serán actualizados
            Doctor objDoctor  = (Doctor) obj;
            objPreparedStatement.setString(1, objDoctor.getName());
            objPreparedStatement.setString(2, objDoctor.getSurname());
            objPreparedStatement.setInt(3, objDoctor.getObjSpeciality().getId());
            objPreparedStatement.setInt(4, objDoctor.getId());

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error set info update doctor: " + e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
        return objPreparedStatement;
    }
}
