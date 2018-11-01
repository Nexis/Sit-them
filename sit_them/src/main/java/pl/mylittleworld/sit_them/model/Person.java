package pl.mylittleworld.sit_them.model;


public class Person {

    private int personID;
    private String name;

    public Person(int personID,String name) {
        this.personID = personID;
        this.name=name;
    }

    public int getPersonID() {
        return personID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

