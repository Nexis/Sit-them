package pl.mylittleworld.database;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class PersonT {

    public PersonT(String name) {
        this.name = name;
    }
    public PersonT(String name, int id) {
        this.name = name;
        personID=id;
    }


    @PrimaryKey(autoGenerate = true)
    private int personID;

    private String name;

    public int getPersonID() {
        return personID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }
}
