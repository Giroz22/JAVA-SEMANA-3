package entity;

public class Passenger {
    private int id;
    private String name;
    private String surname;
    private String identity_card;

    public Passenger() {
    }

    public Passenger(int id, String name, String surname, String identity_card) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.identity_card = identity_card;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getIdentity_card() {
        return identity_card;
    }

    public void setIdentity_card(String identity_card) {
        this.identity_card = identity_card;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", identity_card='" + identity_card + '\'' +
                '}';
    }
}
