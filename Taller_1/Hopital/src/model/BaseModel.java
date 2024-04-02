package model;

import config.ConfigDB;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseModel implements Model {

    private String nameTable = "";

    public BaseModel(String nameTable) {
        this.nameTable = nameTable;
    }

    public abstract Object getInfoObject(ResultSet objResult);
    public abstract PreparedStatement setInfoSave(Object obj);
    public abstract PreparedStatement setInfoUpdate(int id, Object obj);

    @Override
    public List<Object> findAll() {
        // 1. Inicializamos la lista
        List<Object> listObj = new ArrayList<>();

        //  2. Abrimos la conexión
        if(!ConfigDB.openConnection()) return listObj;

        // 3. Creamos la consulta
        String sql = "SELECT * FROM " + this.nameTable;

        try{
            // 4.Preparamos el PreparedStatement
            PreparedStatement objPreparedStatement = ConfigDB.objConnection.prepareStatement(sql);

            // 5. Ejecutamos la consulta
            ResultSet objResult = objPreparedStatement.executeQuery();

            //6. Obtenemos la info
            while (objResult.next()){
                //7. dependiendo de la clase se devuelve un objeto u otro
                Object objDB = getInfoObject(objResult);

                // 8. Agregamos el objeto a la lista
                listObj.add(objDB);
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error find all data");
        }

        ConfigDB.closeConnection();
        return listObj;
    }

    @Override
    public Object findById(int id) {
        Object objDB = null;

        // 1. Abrimos la conexión
        if(!ConfigDB.openConnection()) return null;

        // 2. Preparamos la consulta
        String sql = "SELECT * FROM "+ this.nameTable +" WHERE id = ?;";

        try{
            // 3. Preparamos  el PreparedStatement
            PreparedStatement objPreparedStatement = ConfigDB.objConnection.prepareStatement(sql);
            objPreparedStatement.setInt(1, id);

            // 4. Ejecutamos la consulta
            ResultSet objResult = objPreparedStatement.executeQuery();

            while (objResult.next()){
                // 6. Obtenemos la info dependiendo del obj
                objDB = getInfoObject(objResult);
            }

            //5. Validamos si se encontraron datos con esa id
            if(objDB == null){
                JOptionPane.showMessageDialog(null, "Not found data with that id");
            }
        }catch (SQLException e){
            System.err.println("Error find by id");
        }

        ConfigDB.closeConnection();
        return objDB;
    }

    @Override
    public Object save(Object obj) {
        Object objDB = null;

        try{
            // 1. Abrimos la conexión
            if(!ConfigDB.openConnection()) return null;

            PreparedStatement objPreparedStatement = this.setInfoSave(obj);

            // 5. Ejecutamos la consulta
            objPreparedStatement.execute();
            ResultSet objResult = objPreparedStatement.getGeneratedKeys();
            if(!objResult.next()) throw new SQLException("It wasn't posible to save");

            // 6. Obtenemos el objeto guardado en la BD
            objDB = this.findById(objResult.getInt(1));

        }catch (SQLException e){
            System.err.println("Error to save class BaseModel\n"+e.getMessage());
        }

        ConfigDB.closeConnection();
        return objDB;
    }

    @Override
    public Object update(int id, Object newObj) {
        try{
            // 1. Abrimos la conexión
            if(!ConfigDB.openConnection()) return null;

            PreparedStatement objPreparedStatement = this.setInfoUpdate(id, newObj);

            // 5. Ejecutamos la consulta
            int rowsUpdate = objPreparedStatement.executeUpdate();

            if(rowsUpdate <= 0){
                JOptionPane.showMessageDialog(null,"It wasn't posible to save");
            }

        }catch (SQLException e){
            System.err.println("Error to save in class BaseModel\n"+e.getMessage());
        }

        ConfigDB.closeConnection();
        return newObj;
    }

    @Override
    public boolean delete(int id) {
        boolean isDeleted = false;

        // 1. abrimos la conexión
        if(!ConfigDB.openConnection()) return false;

        // 2. Preparamos la consulta
        String sql = "DELETE FROM " + this.nameTable + " WHERE id = " + id;

        try {

            // 3. Preparamos el PreparedStatement
            PreparedStatement objPreparedStatement = ConfigDB.objConnection.prepareStatement(sql);

            // 4. Ejecutamos la consulta
            int rowsAffected = objPreparedStatement.executeUpdate();

            // 5. Validamos si se afecto la tabla
            if(rowsAffected <= 0){
                JOptionPane.showMessageDialog(null, "Not found data with that id");
            }else {
                isDeleted = true;
            }
        }catch (SQLException e){
            System.err.println("Error delete class base modal\n" + e.getMessage());
        }

        ConfigDB.closeConnection();
        return isDeleted;
    }
}
