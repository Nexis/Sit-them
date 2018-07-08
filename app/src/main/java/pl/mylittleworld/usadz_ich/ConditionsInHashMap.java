package pl.mylittleworld.usadz_ich;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ConditionsInHashMap implements Condition  {

    private HashMap<CONDITIONS_OPTIONS,Integer> conditions_option_hash_map= new HashMap<>();

    public List<Integer> getConditionOptionsValues (CONDITIONS_OPTIONS conditions_option) {
        List<Integer> conditionOptionsValues = new ArrayList<>();

        while(conditions_option_hash_map.get(conditions_option)!=null);
        return null;
    }

}
