package pl.mylittleworld.usadz_ich.conditions;

import pl.mylittleworld.database.tables.GroupT;
import pl.mylittleworld.database.tables.PersonT;
import pl.mylittleworld.database.temporary_storage.Seat;
import pl.mylittleworld.usadz_ich.Group;
import pl.mylittleworld.usadz_ich.SittingPlan;

public class MustInGroupCondition  implements Condition {


    private PersonT person;
    private GroupT group;
    private int priority;

    private int conditionId;
    private final CONDITIONS_OPTIONS conditionType=CONDITIONS_OPTIONS.MUST_IN_GROUP;

    public MustInGroupCondition(PersonT person, GroupT groupT, int id, int priority) {
        this.person = person;
        this.group = groupT;
        this.conditionId =id;
        this.priority = priority;
    }

    public int getPersonId() {
        return person.getPersonID();
    }

    public int getGroupId() {
        return group.getGroupID();
    }

    /**
     *Check if at least one other person from group sits next to this one
     * @param sittingPlan in which the condition fulfillment will be checked
     * @return if this condition is fulfilled in a given sittingPlan
     */
    @Override
    public boolean isThisConditionFulfilled(SittingPlan sittingPlan) {
        Group groupPerson=sittingPlan.getConditions().getPeopleInThisGroup(group.getGroupID());
            for(int id : groupPerson.getPeopleInGroup()){
                if(id!=person.getPersonID()) {
                    Seat seat1 = sittingPlan.whereSits(id);
                    Seat seat2 = sittingPlan.whereSits(person.getPersonID());
                    if (seat1.areThoseSitsCloseToEachOther(seat2, true, false)) {
                        return true;
                    }
                }
            }
       return false;
    }
    /**
     * @return description of condition
     */
    @Override
    public String getDescription() {
        return person.getName() + " MUSI W GRUPIE " + group.getGroupName() +  "   PRIORYTET: " + priority ;
    }

    @Override
    public int getConditionId() {
        return conditionId;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public CONDITIONS_OPTIONS getType() {
        return conditionType;
    }


}
