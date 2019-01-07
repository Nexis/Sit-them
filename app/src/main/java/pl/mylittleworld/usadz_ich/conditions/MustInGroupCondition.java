package pl.mylittleworld.usadz_ich.conditions;

import pl.mylittleworld.database.tables.GroupT;
import pl.mylittleworld.database.tables.PersonT;
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


    /**
     *
     * @param sittingPlan in which the condition fulfillment will be checked
     * @return if this condition is fulfilled in a given sittingPlan
     */
    @Override
    public boolean isThisConditionFulfilled(SittingPlan sittingPlan) {

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
}
