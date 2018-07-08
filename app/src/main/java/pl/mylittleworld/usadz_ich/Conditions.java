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

    public boolean addCondition(){
        return false;
    }
    public boolean removeCondition(){
        return false;
    }
    public boolean areAllConditionsFulfilled(){
        return false;
    }
}
