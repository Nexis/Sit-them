package pl.mylittleworld.usadz_ich.conditions.conditions_descriptors;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import pl.mylittleworld.database.tables.ConditionT;
import pl.mylittleworld.database.tables.PersonT;
import pl.mylittleworld.usadz_ich.conditions.Condition;

public class ConditionDescriptors {

    private ArrayList<ConDescryptor> list= new ArrayList<>();

    public void addDescryptor(ConDescryptor conDescryptor){
        list.add(conDescryptor);
    }

    public void removeDescryptor(ConDescryptor conDescryptor){
        list.remove(conDescryptor);
    }

    private Condition askAllDescryptorsAbout(ConditionT conditionT){
        Condition temp;
        for(ConDescryptor conDescryptor: list){
          temp=conDescryptor.constructConditionObject(conditionT);
            if(temp!=null){
                return temp;
            }
        }
        throw new NoSuchElementException("No such Condition type");
    }

    public ArrayList<Condition> descryptConditionT(List<ConditionT> conditionsT){
        ArrayList<Condition> conditionsList= new ArrayList<>();

        for (ConditionT conditionT : conditionsT) {
            conditionsList.add(askAllDescryptorsAbout(conditionT));
        }
        return conditionsList;
    }
    public Condition descryptOneConditionT(ConditionT condition){
        return askAllDescryptorsAbout(condition);
    }
}
