package pl.mylittleworld.database.tables;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import pl.mylittleworld.usadz_ich.conditions.CONDITIONS_OPTIONS;

/**
 * This class represents a condition witch user give on planning algortm
 * It has two ids which represents two instances and a type which tells what relation should be between this instances
 */
@Entity
public class ConditionT {

   public ConditionT(int id1, int id2, CONDITIONS_OPTIONS conditionType,int priority) {
        this.id1 = id1;
        this.id2 = id2;
        this.conditionType = conditionType;
        this.priority=priority;
    }


    @PrimaryKey(autoGenerate = true)
    private int conditionID;

    private int id1;
    private int id2;

    private int priority;

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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setConditionType(CONDITIONS_OPTIONS conditionType) {
        this.conditionType = conditionType;
    }

}
