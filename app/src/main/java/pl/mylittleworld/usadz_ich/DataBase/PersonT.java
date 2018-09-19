package pl.mylittleworld.usadz_ich.DataBase;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
class PersonT {

    @PrimaryKey
    private int personID;

    private String nazwa;

    public int getPersonID() {
        return personID;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
}
