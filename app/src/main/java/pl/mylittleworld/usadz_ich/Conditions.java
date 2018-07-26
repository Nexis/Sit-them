package pl.mylittleworld.usadz_ich;


import java.util.ArrayList;
import java.util.List;

import pl.mylittleworld.usadz_ich.conditions.Condition;

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
}
