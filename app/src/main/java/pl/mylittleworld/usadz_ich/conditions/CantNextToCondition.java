package pl.mylittleworld.usadz_ich.conditions;

import pl.mylittleworld.database.Seat;
import pl.mylittleworld.database.tables.PersonT;
import pl.mylittleworld.usadz_ich.SittingPlan;
import pl.mylittleworld.usadz_ich.genetics.PersonNotSittedException;

public class CantNextToCondition implements Condition {


    private PersonT person1;
    private PersonT person2;

    private int conitionId;

    public CantNextToCondition(PersonT person1ID, PersonT person2ID, int id) {
        this.person1 = person1ID;
        this.person2 = person2ID;
        this.conitionId=id;
    }


    @Override
    public boolean isThisConditionFulfilled(SittingPlan sittingPlan) {

        Seat seat1 = sittingPlan.whereSits(person1.getPersonID());
        Seat seat2 = sittingPlan.whereSits(person2.getPersonID());

        if(seat1 !=null && seat2 !=null)
            return !(seat1.areThoseSitsCloseToEachOther(seat2,true,true));


        throw new PersonNotSittedException();
    }

    @Override
    public String getDescription() {
        return person1.getName() + " NIE MOŻE OBOK " + person2.getName() ;
    }

    @Override
    public int getConditionId() {
        return conitionId;
    }
}
