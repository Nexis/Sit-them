package pl.mylittleworld.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class TableT {

    @PrimaryKey
    private int tableID;

    private int tableHeight;
    private int tableWidth;
    private int x;
    private int y;

    public int getTableID() {
        return tableID;
    }

    public int getTableHeight() {
        return tableHeight;
    }

    public int getTableWidth() {
        return tableWidth;
    }

    public void setTableWidth(int tableWidth) {
        this.tableWidth = tableWidth;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setTableID(int tableID) {
        this.tableID = tableID;
    }

    public void setTableHeight(int tableHeight) {
        this.tableHeight = tableHeight;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
