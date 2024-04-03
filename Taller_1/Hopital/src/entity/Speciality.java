package entity;

public class Speciality {
    //Attributes
    private int id_speciality;
    private String name;
    private String description;

    //Constructors
    public Speciality() {
    }

    public Speciality(int id_speciality, String name, String description) {
        this.id_speciality = id_speciality;
        this.name = name;
        this.description = description;
    }
    //Methods
    //Setter and Getters
    public int getId() {
        return id_speciality;
    }

    public void setId(int id_speciality) {
        this.id_speciality = id_speciality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //ToString
    @Override
    public String toString() {
        return "Speciality{" +
                "\nName='" + name + '\'' +
                "\nDescription='" + description + '\'' +
                '}';
    }
}
