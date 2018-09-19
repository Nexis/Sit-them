package pl.mylittleworld.usadz_ich.DataBase;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class ChairT {

    @PrimaryKey
    private int tableID;

    @PrimaryKey
    private int x;

    @PrimaryKey
    private int y;

    public int getTableID() {
        return tableID;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
