package model;

import config.ConfigDB;

import javax.swing.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseModel<T> implements CRUD<T> {

    private final String nameTable;
    private final Class<?> entityClass;

    public BaseModel(T entity)  {
        this.entityClass = entity.getClass();
        this.nameTable = this.entityClass.getSimpleName();
    }

    @Override
    public List<T> findAll() {
        // Inicializamos la lista
        List<T> listObj = new ArrayList<>();

        // Abrimos la conexión
        if(!ConfigDB.openConnection()) return listObj;

        // Creamos la consulta
        String sql = "SELECT * FROM " + this.nameTable;

        try{
            // Preparamos el PreparedStatement
            PreparedStatement objPreparedStatement = ConfigDB.objConnection.prepareStatement(sql);

            // Ejecutamos la consulta
            ResultSet objResult = objPreparedStatement.executeQuery();

            // Obtenemos la info
            while (objResult.next()){
                T objDB = getInfoObject(objResult);
                if (objDB == null) break;

                // Agregamos el objeto a la lista
                listObj.add(objDB);
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error find all data" + e.getMessage() );
        }

        ConfigDB.closeConnection();
        return listObj;
    }
    @Override
    public T findById(int id) {
        T objDB = null;

        // Abrimos la conexión
        if(!ConfigDB.openConnection()) return null;

        // Preparamos la consulta
        String sql = "SELECT * FROM "+ this.nameTable +" WHERE id = ?;";

        try{
            // Preparamos  el PreparedStatement
            PreparedStatement objPreparedStatement = ConfigDB.objConnection.prepareStatement(sql);
            objPreparedStatement.setInt(1, id);

            // Ejecutamos la consulta
            ResultSet objResult = objPreparedStatement.executeQuery();

            while (objResult.next()){
                // Obtenemos la info dependiendo del obj
                objDB = getInfoObject(objResult);
            }

            // Validamos si se encontraron datos con esa id
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
    public T save(T objSave) {
        T newObj = null;

        try{
            // Abrimos la conexión
            if(!ConfigDB.openConnection()) return null;

            // Buscamos todos los atributos
            Field[] listAttributes = this.entityClass.getDeclaredFields();

            // Preparamos el PreparedStatement
            String sql = generateQueryInsert(listAttributes);
            PreparedStatement objPreparedStatement = ConfigDB.objConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Recorremos la lista de atributos
            for (int i = 1; i<listAttributes.length; i++) {
                // Obtenemos el nombre del atributo
                Field attribute = listAttributes[i];

                // Obtenemos el nombre del método get
                String nameMethodSet = getNameMethodGet(attribute);

                // Obtenemos el método set del atributo y enviamos la información
                Method methodGet = this.entityClass.getDeclaredMethod(nameMethodSet);
                objPreparedStatement.setObject(i,methodGet.invoke(objSave));
            }

            // Ejecutamos la consulta
            objPreparedStatement.execute();
            ResultSet objResult = objPreparedStatement.getGeneratedKeys();

            if(!objResult.next()) throw new SQLException("It wasn't posible to save");

            // Obtenemos el objeto guardado en la BD
            newObj = this.findById(objResult.getInt(1));

        }catch (Exception e){
            System.err.println("Error to save class BaseModel\n"+e.getMessage());
        }

        ConfigDB.closeConnection();
        return newObj;
    }
    @Override
    public T update(T newObj) {
        try{
            // Abrimos la conexión
            if(!ConfigDB.openConnection()) return null;

            // Buscamos todos los atributos
            Field[] listAttributes = this.entityClass.getDeclaredFields();

            // Preparamos el PreparedStatement
            String sql = generateQueryUpdate(listAttributes);
            PreparedStatement objPreparedStatement = ConfigDB.objConnection.prepareStatement(sql);

            // Recorremos cada uno de los atributos
            for (int i = 0; i<listAttributes.length; i++) {
                // Obtenemos el nombre del atributo
                Field attribute = listAttributes[i];
                String nameMethodGet = getNameMethodGet(attribute);

                // Obtenemos el método get del atributo para enviar la información
                Method methodGet = this.entityClass.getDeclaredMethod(nameMethodGet);

                // Si el atributo es el id se asigna en el query al final
                if(attribute.getName().equals("id")){
                    objPreparedStatement.setObject(listAttributes.length, methodGet.invoke(newObj));
                    continue;
                }

                objPreparedStatement.setObject(i, methodGet.invoke(newObj));
            }

            objPreparedStatement.executeUpdate();

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error to save in class BaseModel\n"+e.getMessage());
        }

        ConfigDB.closeConnection();
        return newObj;
    }
    @Override
    public boolean delete(int id) {
        boolean isDeleted = false;

        // Abrimos la conexión
        if(!ConfigDB.openConnection()) return false;

        // Preparamos la consulta
        String sql = "DELETE FROM " + this.nameTable + " WHERE id = " + id;

        try {
            // Preparamos el PreparedStatement
            PreparedStatement objPreparedStatement = ConfigDB.objConnection.prepareStatement(sql);

            // Ejecutamos la consulta
            int rowsAffected = objPreparedStatement.executeUpdate();

            // Validamos si se afecto la tabla
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

    public T getInfoObject(ResultSet objResult){
        T obj = null;
        try {
            //Instanciamos el objeto
            obj = (T) this.entityClass.getDeclaredConstructor().newInstance();

            //Obtenemos los atributos del objeto
            Field[] listAttributes = this.entityClass.getDeclaredFields();

            //Recorremos la lista de atributos
            for (int i = 0; i < listAttributes.length; i++) {
                //Obtenemos el atributo
                Field attribute = listAttributes[i];

                //Creamos el método set del objeto
                String nameMethodSet = getNameMethodSet(attribute);
                Class<?> typeAttribute = attribute.getType();
                Method methodSet = this.entityClass.getDeclaredMethod(nameMethodSet, typeAttribute);

                //Obtenemos el valor del atributo desde la DB
                Object valueAttribute = objResult.getObject(i + 1);

                //Asignamos un valor al atributo
                methodSet.invoke(obj, valueAttribute);
            }
        }catch (NoSuchMethodException e){
                System.out.println("Method not found\n" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error get info\n" + e);
        }

        return obj;
    }
    private String getNameMethodGet(Field attribute){
        String nameAttribute = attribute.getName();
        return "get" + nameAttribute.substring(0,1).toUpperCase() + nameAttribute.substring(1);
    }
    private String getNameMethodSet(Field attribute){
        String nameAttribute = attribute.getName();
        return "set" + nameAttribute.substring(0,1).toUpperCase() + nameAttribute.substring(1);
    }
    private String generateQueryInsert(Field[] listAttributes){
        String listNameAttributes = "";
        String numValues = "";

        //Recorremos la lista de atributos y obtenemos la lista de atributos
        for (int i = 1; i<listAttributes.length; i++) {
            //Obtenemos el nombre del atributo
            Field attribute = listAttributes[i];
            if(i<listAttributes.length-1) {
                listNameAttributes += attribute.getName() + ", ";
                numValues += "?,";
            } else {
                listNameAttributes += attribute.getName();
                numValues += "?";
            }
        }
        //Preparamos el PreparedStatement
        return "INSERT INTO " + this.nameTable + " (" + listNameAttributes + ") VALUES ( " + numValues + " );";
    }
    private String generateQueryUpdate(Field[] listAttributes){
        String strNamesAttributes = "";

        for (int i = 1; i<listAttributes.length; i++) {
            //Obtenemos el atributo
            Field attribute = listAttributes[i];
            if(i<listAttributes.length-1) {
                strNamesAttributes += attribute.getName() + "=?, ";
            } else {
                strNamesAttributes += attribute.getName() + "=?";
            }
        }

        return "UPDATE "+ this.nameTable +" SET "+ strNamesAttributes +" WHERE id=?";
    }

}