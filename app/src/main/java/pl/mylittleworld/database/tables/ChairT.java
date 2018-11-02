package pl.mylittleworld.database.tables;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class ChairT {

    public ChairT(int tableID, int x, int y) {
        this.tableID = tableID;
        this.x = x;
        this.y = y;
    }

    @PrimaryKey
    private int tableID;


    private int x;
    private int y;

    public void setTableID(int tableID) {
        this.tableID = tableID;
    }

    public int getTableID() {
        return tableID;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
