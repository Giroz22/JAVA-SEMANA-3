package model;

import config.ConfigDB;
import entity.Speciality;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SpecialityModel extends BaseModel{
    public SpecialityModel() {
        super("specialities");
    }

    @Override
    public Object getInfoObject(ResultSet objResult) {
        Object objSpeciality = null;
        try{
            int id_speciality = objResult.getInt(1);
            String name = objResult.getString(2);
            String description = objResult.getString(3);

            objSpeciality = new Speciality(id_speciality,name,description);
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error get info speciality: " + e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
        return objSpeciality;
    }

    @Override
    public PreparedStatement setInfoSave(Object obj) {
        PreparedStatement objPreparedStatement = null;
        try {
            //Preparamos el PreparedStatement
            String sql = "INSERT INTO specialities(name, description) VALUES (?,?);";
            objPreparedStatement = ConfigDB.objConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //Asignamos los valores que se van a enviar
            Speciality objSpeciality = (Speciality) obj;
            objPreparedStatement.setString(1,objSpeciality.getName());
            objPreparedStatement.setString(2,objSpeciality.getDescription());

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error set info save speciality: " + e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
        return objPreparedStatement;
    }

    @Override
    public PreparedStatement setInfoUpdate(int id, Object obj) {
        PreparedStatement objPreparedStatement = null;
        try{
            //Preparamos el PreparedStatement
            String sql = "UPDATE specialities SET name=? description=? WHERE id=?";
            objPreparedStatement = ConfigDB.objConnection.prepareStatement(sql);

            //Asignamos los valores que serán actualizados
            Speciality objSpeciality  = (Speciality) obj;
            objPreparedStatement.setString(1, objSpeciality.getName());
            objPreparedStatement.setString(2, objSpeciality.getDescription());
            objPreparedStatement.setInt(3, id);

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error set info update speciality: " + e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
        return objPreparedStatement;
    }
}
