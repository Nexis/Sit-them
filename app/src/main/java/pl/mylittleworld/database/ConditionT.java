package pl.mylittleworld.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import pl.mylittleworld.sit_them.conditions.CONDITIONS_OPTIONS;

@Entity
public class ConditionT {

    @PrimaryKey
    private int conditionID;

    private int id1;
    private int id2;

    private CONDITIONS_OPTIONS conditionType;

    public int getConditionID() {
        return conditionID;
    }

    public void setConditionID(int conditionID) {
        this.conditionID = conditionID;
    }

    public int getId1() {
        return id1;
    }

    public void setId1(int id1) {
        this.id1 = id1;
    }

    public int getId2() {
        return id2;
    }

    public void setId2(int id2) {
        this.id2 = id2;
    }

    public CONDITIONS_OPTIONS getConditionType() {
        return conditionType;
    }

    public void setConditionType(CONDITIONS_OPTIONS conditionType) {
        this.conditionType = conditionType;
    }
}
