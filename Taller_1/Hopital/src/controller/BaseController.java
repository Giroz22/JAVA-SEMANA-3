package controller;

import model.BaseModel;

import javax.swing.*;
import java.util.List;

public abstract class  BaseController {
    private BaseModel modelBase = null;

    public BaseController(BaseModel modelBase) {
        this.modelBase = modelBase;
    }

    public abstract Object requestData(int id);

    public void getAll(){
        //1. obtenemos la lista de autores
        List<Object> listObj = modelBase.findAll();

        //2.Validamos la info
        if(listObj.isEmpty()) return;

        //3. Recorremos la lista
        String strListObj =  getAll(listObj);


        //4. Mostramos la info
        JOptionPane.showMessageDialog(null, "List All:\n"+ strListObj);
    }

    public String getAll(List<Object> listObj){
        String strListObj =  "";

        for( Object obj: listObj){
            //3.Agregamos la info al string
            strListObj += obj.toString() + "\n";
        }

        return strListObj;
    }

    public void getById(){
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Write id to find: "));

            Object obj = modelBase.findById(id);

            if (obj == null) return;

            JOptionPane.showMessageDialog(null, "Info: \n" + obj.toString());
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "ID invalid");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error getId");
        }
    }

    public void create(){
        Object objSave = this.requestData(0);
        if(objSave == null) return;

        Object objDB = this.modelBase.save(objSave);
        if(objDB == null) return;

        //Mostramos
        JOptionPane.showMessageDialog(null, "Add successful\n" + objDB.toString());
    }
    public void update(){

        //Obtenemos todos los objetos y los mostramos para que el usuario seleccione cuál actualizara
        int idObj = selectIdObject();
        if(idObj == -1) return; //Validamos si se obtuvo el id correctamente

        // Obtenemos la info tanto del objeto nuevo como del antiguo
        Object objOld = this.modelBase.findById(idObj);
        if (objOld == null) return; // Validamos la info

        Object objUpdated = this.requestData(idObj);
        if (objUpdated == null) return; // Validamos la info

        //Mostramos el mensaje de confirmación
        int isSure = JOptionPane.showConfirmDialog(null, "Are you sure of update?\n"
                + "Old:\n" + objOld.toString() + "\n"
                + "New:\n" + objUpdated.toString());

        if(isSure == 0){
            //Actualizamos el objeto
            this.modelBase.update(idObj, objUpdated);
            JOptionPane.showMessageDialog(null, "Successfully updated");
        }else {
            JOptionPane.showMessageDialog(null, "Update canceled");
        }

    }
    public void delete(){
        //Obtenemos todos los objetos y los mostramos para que el usuario seleccione cuál eliminará
        int idObj = this.selectIdObject();
        if(idObj == -1) return; //Validamos si se obtuvo el id correctamente

        Object obj = modelBase.findById(idObj);
        if(obj == null) return; //Validamos

        //Mostramos el mensaje de confirmación
        int isSure = JOptionPane.showConfirmDialog(null, "Are you sure of delete?\n"
                + obj.toString());

        if(isSure == 0){
            // Eliminamos el objeto
            if (!this.modelBase.delete(idObj)) return; // Validamos si se eliminó el objeto

            JOptionPane.showMessageDialog(null, "Successfully deleted");
        }else {
            JOptionPane.showMessageDialog(null, "Update canceled");
        }
    }

    //Obtenemos todos los objetos y los mostramos para que el usuario seleccione cuál eliminará
    public int selectIdObject(){
        int idObj = -1;

        try{
            List<Object> listObj = this.modelBase.findAll();
            String strListObj = this.getAll(listObj);
            idObj = Integer.parseInt(JOptionPane.showInputDialog(null, "Write the id:\n" + strListObj));

            //Validamos la información
            if(modelBase.findById(idObj) == null) return idObj;

        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "The id must be an integer");

        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error select an object");
        }

        return idObj;
    }

}
