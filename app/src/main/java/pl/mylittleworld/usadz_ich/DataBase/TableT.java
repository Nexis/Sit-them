package pl.mylittleworld.usadz_ich.DataBase;

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

}
