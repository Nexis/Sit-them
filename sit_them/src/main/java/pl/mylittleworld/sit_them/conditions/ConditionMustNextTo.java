package pl.mylittleworld.sit_them.conditions;

import pl.mylittleworld.sit_them.PersonNotSittedException;
import pl.mylittleworld.sit_them.model.Person;
import pl.mylittleworld.sit_them.model.Seat;
import pl.mylittleworld.sit_them.SittingPlanProxy;


public class ConditionMustNextTo implements Condition {

    private Person person1;
    private Person person2;

    public ConditionMustNextTo(Person person1ID, Person person2ID) {
        this.person1 = person1ID;
        this.person2 = person2ID;
    }

    public boolean isThisConditionFulfilled(SittingPlanProxy sittingPlanProxy){
        Seat seat1 = sittingPlanProxy.whereSits(person1.getPersonID());
        Seat seat2 = sittingPlanProxy.whereSits(person2.getPersonID());

       if(seat1 !=null && seat2 !=null)
         return seat1.areThoseSitsCloseToEachOther(seat2,false,false);

       throw new PersonNotSittedException();
   }

    @Override
    public String getDescription() {
        return person1.getName() + " OBOK " + person2.getName() ;
    }


}
