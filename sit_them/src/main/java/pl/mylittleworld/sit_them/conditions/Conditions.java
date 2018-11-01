package pl.mylittleworld.sit_them.conditions;


import java.util.ArrayList;
import java.util.List;

import pl.mylittleworld.sit_them.SittingPlanProxy;
import pl.mylittleworld.sit_them.conditions.Condition;

public class Conditions {

    private List<Condition> conditions;

    public Conditions() {
        this.conditions=new ArrayList<>();
    }

    public Conditions(List<Condition> conditions) {
        this.conditions = conditions;
    }

    public boolean addCondition(Condition condition){
        return conditions.add(condition);
    }

    public boolean removeCondition(Condition condition){

        return conditions.remove(condition);
    }

    public boolean areAllConditionsFulfilled(SittingPlanProxy sittingPlanProxy){
        for(Condition condition : conditions){
            if(!condition.isThisConditionFulfilled(sittingPlanProxy)) {
                return false;
            }
        }
        return true;
    }
    public int howMuchAreConditionsFullfield(SittingPlanProxy sittingPlanProxy){
        int numberOfConditions=0;
        int numberOfFullFieldConditions=0;
        for(Condition condition : conditions){
            if(condition.isThisConditionFulfilled(sittingPlanProxy)) {
                ++numberOfFullFieldConditions;
            }
            ++numberOfConditions;
        }
       if(numberOfConditions!=0){
            return (numberOfFullFieldConditions*100 /numberOfConditions);
       }
       else return 0;
    }
}
