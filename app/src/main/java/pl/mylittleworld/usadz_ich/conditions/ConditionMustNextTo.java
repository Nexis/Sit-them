package pl.mylittleworld.usadz_ich.conditions;

import pl.mylittleworld.usadz_ich.PersonNotSittedException;
import pl.mylittleworld.usadz_ich.Sit;
import pl.mylittleworld.usadz_ich.SittingPlanProxy;

public class ConditionMustNextTo implements Condition {

    private int person1ID;
    private int person2ID;

    public ConditionMustNextTo(int person1ID, int person2ID) {
        this.person1ID = person1ID;
        this.person2ID = person2ID;
    }

    public boolean isThisConditionFulfilled(SittingPlanProxy sittingPlanProxy){
        Sit sit1= sittingPlanProxy.whereSits(person1ID);
        Sit sit2= sittingPlanProxy.whereSits(person2ID);

       if(sit1!=null && sit2!=null)
         return sit1.areThisSitsCloseToEachOther(sit2,false,false);

       throw new PersonNotSittedException();
   }


}
