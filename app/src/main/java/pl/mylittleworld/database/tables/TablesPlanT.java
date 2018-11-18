package pl.mylittleworld.database.tables;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


@Entity
public class TablesPlanT {

    public TablesPlanT(int roomHeight, int roomWidth) {
        this.roomHeight = roomHeight;
        this.roomWidth = roomWidth;
    }

    @PrimaryKey
    private int tablesPlanID;

    private int roomHeight;
    private int roomWidth;


    public int getTablesPlanID() {
        return tablesPlanID;
    }

    public void setTablesPlanID(int tablesPlanID) {
        this.tablesPlanID = tablesPlanID;
    }

    public int getRoomHeight() {
        return roomHeight;
    }

    public void setRoomHeight(int roomHeight) {
        this.roomHeight = roomHeight;
    }

    public int getRoomWidth() {
        return roomWidth;
    }

    public void setRoomWidth(int roomWidth) {
        this.roomWidth = roomWidth;
    }

}
