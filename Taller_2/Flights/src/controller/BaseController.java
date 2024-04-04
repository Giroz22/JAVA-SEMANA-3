package controller;

import model.BaseModel;

import javax.swing.*;
import java.util.List;
import java.lang.reflect.Method;

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
        try {
            // Obtenemos la info tanto del objeto nuevo como del antiguo
            Object objOld = selectObject();
            if (objOld == null) return; // Validamos la info

            // Obtenemos el método getId() del objeto
            Method getIdMethod = objOld.getClass().getMethod("getId");

            // Invocamos el método getId() en el objeto para obtener el ID
            int idObj = (int) getIdMethod.invoke(objOld);

            Object objUpdated = this.requestData(idObj);
            if (objUpdated == null) return; // Validamos la info

            //Mostramos el mensaje de confirmación
            int isSure = JOptionPane.showConfirmDialog(null, "Are you sure of update?\n"
                    + "Old:\n" + objOld.toString() + "\n"
                    + "New:\n" + objUpdated.toString());

            if (isSure == 0) {
                //Actualizamos el objeto
                Object obj = this.modelBase.update(objUpdated);
                if (obj != null) {
                    JOptionPane.showMessageDialog(null, "Successfully updated");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Update canceled");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error update " + e.getMessage());
        }
    }
    public void delete(){
        try {
            //Obtenemos el objeto que se eliminara
            Object obj = this.selectObject();
            if (obj == null) return; //Validamos

            //Mostramos el mensaje de confirmación
            int isSure = JOptionPane.showConfirmDialog(null, "Are you sure of delete?\n"
                    + obj.toString());

            if (isSure == 0) {
                // Eliminamos el objeto
                // Obtenemos el método getId() del objeto
                Method getIdMethod = obj.getClass().getMethod("getId");

                // Invocamos el método getId() en el objeto para obtener el ID
                int idObj = (int) getIdMethod.invoke(obj);
                if (!this.modelBase.delete(idObj)) return; // Validamos si se eliminó el objeto

                JOptionPane.showMessageDialog(null, "Successfully deleted");
            } else {
                JOptionPane.showMessageDialog(null, "Update canceled");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error occurred while deleting" + e.getMessage() );
        }
    }

    //Obtenemos todos los objetos y los mostramos para que el usuario seleccione cuál eliminará
    public Object selectObject(){
        Object objSelected = null;

        try{
            Object[] listObj = this.modelBase.findAll().toArray();
            objSelected = (JOptionPane.showInputDialog(
                    null,
                    "Select an option:",
                    "Options",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    listObj,
                    listObj[0]
            ));
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error select an object");
        }

        return objSelected;
    }

}
