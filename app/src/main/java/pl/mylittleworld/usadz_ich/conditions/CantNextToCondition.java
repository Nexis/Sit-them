package pl.mylittleworld.usadz_ich.conditions;

import pl.mylittleworld.database.temporary_storage.Seat;
import pl.mylittleworld.database.tables.PersonT;
import pl.mylittleworld.usadz_ich.SittingPlan;
import pl.mylittleworld.usadz_ich.genetics.PersonNotSittedException;

/**
 * This class represents condition of type CAN_T_NEXT_TO which means that two people who are given can t seat close to each other
 */
public class CantNextToCondition implements Condition {


    private PersonT person1;
    private PersonT person2;

    private int conditionId;
    private final CONDITIONS_OPTIONS conditionType=CONDITIONS_OPTIONS.CAN_T_NEXT_TO;

    private int priority;

    public CantNextToCondition(PersonT person1ID, PersonT person2ID, int id, int priority) {
        this.person1 = person1ID;
        this.person2 = person2ID;
        this.conditionId=id;
        this.priority = priority;
    }


    /**
     *
     * @param sittingPlan in which the condition fulfillment will be checked
     * @return if this condition is fulfilled in a given sittingPlan
     */
    @Override
    public boolean isThisConditionFulfilled(SittingPlan sittingPlan) {

        Seat seat1 = sittingPlan.whereSits(person1.getPersonID());
        Seat seat2 = sittingPlan.whereSits(person2.getPersonID());

        if(seat1 !=null && seat2 !=null)
            return !(seat1.areThoseSitsCloseToEachOther(seat2,true,true));


        throw new PersonNotSittedException();
    }
    /**
     * @return description of condition
     */
    @Override
    public String getDescription() {
        return person1.getName() + " NIE MOŻE OBOK " + person2.getName() +  "   PRIORYTET: " + priority ;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public CONDITIONS_OPTIONS getType() {
        return conditionType;
    }

    @Override
    public int getConditionId() {
        return conditionId;
    }
}
