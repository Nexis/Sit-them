package pl.mylittleworld.usadz_ich.conditions;


import java.util.ArrayList;
import java.util.List;

import pl.mylittleworld.usadz_ich.SittingPlan;

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

    public List<Condition> getAll(){
        return conditions;
    }

    public boolean removeCondition(Condition condition){

        return conditions.remove(condition);
    }

    public boolean areAllConditionsFulfilled(SittingPlan sittingPlan){
        for(Condition condition : conditions){
            if(!condition.isThisConditionFulfilled(sittingPlan)) {
                return false;
            }
        }
        return true;
    }
    public int howMuchAreConditionsFullfield(SittingPlan sittingPlan){
        int numberOfConditions=0;
        int numberOfFullFieldConditions=0;
        for(Condition condition : conditions){
            if(condition.isThisConditionFulfilled(sittingPlan)) {
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
