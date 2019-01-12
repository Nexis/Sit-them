package pl.mylittleworld.database.temporary_storage;

import java.util.ArrayList;
import java.util.List;

import pl.mylittleworld.database.NameId;
import pl.mylittleworld.database.tables.GroupT;

public class Groups {
    private static List<NameId> nameIdGroups;
    private static List<GroupT> groupTS;

    public void initialize(List<NameId> nameIdGroups, List<GroupT> groupTS){
        Groups.groupTS=groupTS;
        Groups.nameIdGroups=nameIdGroups;
    }

    public static ArrayList<NameId> getGroupsAsNameId(List<GroupT> groups){
        ArrayList<NameId> nameIdGroups=new ArrayList<>();
        for(GroupT groupT : groups){
            nameIdGroups.add(new NameId(groupT.getGroupName(),groupT.getGroupID()));
        }
        Groups.groupTS=groups;
        Groups.nameIdGroups=nameIdGroups;
        return nameIdGroups;
    }
    public static List<NameId> getTemporaryStorageGroupsAsNameId(){
        return nameIdGroups;
    }

    public static List<GroupT> getTemporaryStorageGroups() {
        return groupTS;
    }
}
