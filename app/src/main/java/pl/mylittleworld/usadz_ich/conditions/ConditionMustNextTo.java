package pl.mylittleworld.usadz_ich.conditions;

import pl.mylittleworld.usadz_ich.PersonNotSittedException;
import pl.mylittleworld.usadz_ich.Seat;
import pl.mylittleworld.usadz_ich.SittingPlanProxy;

public class ConditionMustNextTo implements Condition {

    private int person1ID;
    private int person2ID;

    public ConditionMustNextTo(int person1ID, int person2ID) {
        this.person1ID = person1ID;
        this.person2ID = person2ID;
    }

    public boolean isThisConditionFulfilled(SittingPlanProxy sittingPlanProxy){
        Seat seat1 = sittingPlanProxy.whereSits(person1ID);
        Seat seat2 = sittingPlanProxy.whereSits(person2ID);

       if(seat1 !=null && seat2 !=null)
         return seat1.areThoseSitsCloseToEachOther(seat2,false,false);

       throw new PersonNotSittedException();
   }


}
