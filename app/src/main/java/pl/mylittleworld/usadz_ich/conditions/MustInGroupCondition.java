package pl.mylittleworld.usadz_ich.conditions;

import pl.mylittleworld.database.Seat;
import pl.mylittleworld.database.tables.GroupT;
import pl.mylittleworld.database.tables.PersonT;
import pl.mylittleworld.usadz_ich.SittingPlan;
import pl.mylittleworld.usadz_ich.genetics.PersonNotSittedException;

public class MustInGroupCondition  implements Condition {


    private PersonT person;
    private GroupT group;

    private int conitionId;

    public MustInGroupCondition(PersonT person, GroupT groupT, int id) {
        this.person = person;
        this.group = groupT;
        this.conitionId=id;
    }


    @Override
    public boolean isThisConditionFulfilled(SittingPlan sittingPlan) {

       return false;
    }

    @Override
    public String getDescription() {
        return person.getName() + " MUSI W GRUPIE " + group.getGroupName() ;
    }

    @Override
    public int getConditionId() {
        return conitionId;
    }
}
