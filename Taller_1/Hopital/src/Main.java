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
            objController = switch (opc) {
                case "1" -> new SpecialityController();
                case "2" -> new DoctorController();
                case "3" -> new PatientController();
                case "4" -> new AppointmentsController();
                default -> null;
            };

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
