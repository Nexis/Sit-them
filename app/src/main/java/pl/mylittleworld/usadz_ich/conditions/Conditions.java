package pl.mylittleworld.usadz_ich.conditions;


import java.util.ArrayList;
import java.util.List;

import pl.mylittleworld.usadz_ich.SittingPlan;

/**
 * This class can storage pack of conditions and provides method to access them
 * here is also implemented a target function
 */
public class Conditions {

    private List<Condition> conditions;

    public Conditions() {
        this.conditions=new ArrayList<>();
    }

    public Conditions(List<Condition> conditions) {
        this.conditions = conditions;
    }

    /**
     *
     * @param condition
     * @return add given condition to stored
     */
    public boolean addCondition(Condition condition){
        return conditions.add(condition);
    }

    /**
     *
     * @return all stored conditions
     */
    public List<Condition> getAll(){
        return conditions;
    }

    /**
     *
     * @param condition
     * @return remove same condition as given from stored
     */
    public boolean removeCondition(Condition condition){

        return conditions.remove(condition);
    }

    /**
     * This is a target function
     * @param sittingPlan for which it will calculate
     * @return % of fulfilled conditions considering theirs priority
     * in given sittingPlan
     */
    public int howMuchAreConditionsFullfield(SittingPlan sittingPlan){
        int weightOfConditions=0;
        int weightOfFullFieldConditions=0;
        for(Condition condition : conditions){
            if(condition.isThisConditionFulfilled(sittingPlan)) {
                weightOfFullFieldConditions+=condition.getPriority();
            }
            weightOfConditions+=condition.getPriority();
        }
       if(weightOfConditions!=0){
            return (weightOfFullFieldConditions*100 /weightOfConditions);
       }
       else return 0;
    }
}
