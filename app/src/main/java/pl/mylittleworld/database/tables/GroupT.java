package pl.mylittleworld.database.tables;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * A group which can gather some people each group has a name and the id
 */
@Entity
public class GroupT {

    public GroupT(String groupName) {
        this.groupName = groupName;
    }

    @PrimaryKey(autoGenerate = true)
    private int groupID;

    private String groupName;


    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}

