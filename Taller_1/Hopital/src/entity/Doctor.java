package entity;

public class Doctor {
    //Attributes
    private int id_doctor;
    private String name;
    private String surname;
    private Speciality objSpeciality;

    //Constructors
    public Doctor() {
    }

    public Doctor(int id_doctor, String name, String surname, Speciality objSpeciality) {
        this.id_doctor = id_doctor;
        this.name = name;
        this.surname = surname;
        this.objSpeciality = objSpeciality;
    }

    //Methods
    //Setter and Getters
    public int getId() {
        return id_doctor;
    }

    public void setId(int id_doctor) {
        this.id_doctor = id_doctor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Speciality getObjSpeciality() {
        return objSpeciality;
    }

    public void setObjSpeciality(Speciality objSpeciality) {
        this.objSpeciality = objSpeciality;
    }

    //ToString
    @Override
    public String toString() {
        return "Doctor{" +
                "Name='" + name + '\'' +
                "Surname='" + surname + '\'' +
                "ObjSpeciality=" + objSpeciality.getName() +
                '}';
    }
}
