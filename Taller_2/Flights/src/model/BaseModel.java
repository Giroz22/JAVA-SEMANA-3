package model;

import config.ConfigDB;
import entity.BaseEntity;

import javax.swing.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseModel implements CRUD {

    private String nameTable;
    private Class<?> entity;

    public BaseModel(Class<?> entity)  {
        //Se obtiene el nombre de la clase que extiende de BaseModel
        nameTable = entity.getSimpleName();
        this.entity = entity;
    }

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
                Object objDB = getInfoObject(objResult);
                if (objDB == null) return listObj;

                // 8. Agregamos el objeto a la lista
                listObj.add(objDB);
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error find all data" + e.getMessage() );
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
/*
    @Override
    public Object update(Object newObj) {
        try{
            // 1. Abrimos la conexión
            if(!ConfigDB.openConnection()) return null;

            PreparedStatement objPreparedStatement = this.setInfoUpdate(newObj);
            if(objPreparedStatement == null) return null;

            // 5. Ejecutamos la consulta
            int rowsUpdate = objPreparedStatement.executeUpdate();

            if(rowsUpdate <= 0){
                JOptionPane.showMessageDialog(null,"It wasn't posible to save");
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error to save in class BaseModel\n"+e.getMessage());
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
    }*/


    public Object getInfoObject(ResultSet objResult){
        Object obj = "";
        //Field[] listAtributes = this.getClass().toString();
        try {
            obj = entity.getDeclaredConstructor().newInstance();
            Field[] listAttributes = entity.getDeclaredFields();
            for (int i = 0; i<listAttributes.length; i++){
                //Obtenemos el nombre del atributo
                Field attribute = listAttributes[i];
                String nameAttribute = attribute.getName();
                String nameMethodSet = "set" + nameAttribute.substring(0,1).toUpperCase() + nameAttribute.substring(1);

                //Obtenemos el valor de la DB
                Object attributeDB = objResult.getObject(i+1);

                //Obtenemos el método set del atributo para enviar la información
                Method methodSet = this.entity.getDeclaredMethod(nameMethodSet, attribute.getType());

                //Asignamos el valor obtenido de la DB
                Object resultMethod = methodSet.invoke(obj, attributeDB);
            }
        } catch (Exception e) {
            System.out.println("Error al Crear la clase\n" + e);
        }

        return obj;
    }
    public PreparedStatement setInfoSave(Object obj) {
        PreparedStatement objPreparedStatement = null;
        try {
            //Buscamos todos los atributos
            Field[] listAttributes = entity.getDeclaredFields();
            String listNameAttributes = "";
            //Recorremos la lista de atributos y obtenemos la lista de atributos
            for (int i = 0; i<listAttributes.length; i++) {
                //Obtenemos el nombre del atributo
                Field attribute = listAttributes[i];
                listNameAttributes += attribute.getName() + ",";
            }

            //Preparamos el PreparedStatement
            String sql = "INSERT INTO doctors (" + listNameAttributes + ") VALUES (?,?,?);";
            objPreparedStatement = ConfigDB.objConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //Recorremos la lista de atributos
            for (int i = 0; i<listAttributes.length; i++) {
                //Obtenemos el nombre del atributo
                Field attribute = listAttributes[i];
                String nameAttribute = attribute.getName();
                String nameMethodSet = "get" + nameAttribute.substring(0,1).toUpperCase() + nameAttribute.substring(1);

                //Obtenemos el método get del atributo para enviar la información
                Method methodGet = this.entity.getDeclaredMethod(nameMethodSet);
                objPreparedStatement.setObject(i+1,methodGet.invoke(obj));
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error set info save: " + e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
        return objPreparedStatement;
    }
/*
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
 */
}
