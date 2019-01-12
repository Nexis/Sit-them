package pl.mylittleworld.usadz_ich;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is a container for part of conditions which has type MUST_IN_GROUP storing id of group and ids of members
 */
public class Group {
    private int groupId;
    private List<Integer> members= new ArrayList<>();

    public Group(int groupId) {
        this.groupId = groupId;
    }

    public int getGroupId() {
        return groupId;
    }

    public List<Integer> getPeopleInGroup(){
        return members;
    }

    public void addPerson(int id){
        members.add(new Integer(id));
    }
}
