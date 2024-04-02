import config.ConfigDB;
import controller.*;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        String opc = "";

        do{
            //limpiamos el objController
            BaseController objController = null;

            opc = JOptionPane.showInputDialog(null, """
                    Select a option:
                    1. Specialities
                    2. Doctors
                    3. Patients
                    4. Appointments
                    5. Salir
                    """);

            //En base a la opción seleccionada el objController toma un valor u otro
            switch (opc){
                case "1":
                    objController = new SpecialityController();
                    break;
                case "2":
                    objController = new DoctorController();
                    break;
                case "3":
                    objController = new PatientController();
                    break;
                case "4":
                    objController = new AppointmentsController();
                    break;
                case "5":
                    continue;
                default:
                    JOptionPane.showMessageDialog(null, "Option invalid");
                    continue;
            }

            main.menuCrud(objController);

        }while (!opc.equals("5"));
    }

    public void menuCrud(BaseController objController){
        String opc="";
        do{
            opc = JOptionPane.showInputDialog(null, """
                    Select a option:
                    1. FindAll
                    2. FindByID
                    3. Save
                    4. Update
                    5. Delete
                    6. Back
                    """);

            switch (opc){
                case "1":
                    objController.getAll();
                    break;
                case "2":
                    objController.getById();
                    break;
                case "3":
                    objController.create();
                    break;
                case "4":
                    objController.update();
                    break;
                case "5":
                    objController.delete();
                    break;
                case "6":
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Option invalid");
            }
        }while (!opc.equals("6"));
    }
}
