package pl.mylittleworld.usadz_ich.conditions.conditions_descriptors;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import pl.mylittleworld.database.tables.ConditionT;
import pl.mylittleworld.usadz_ich.conditions.Condition;

/**
 * This class is storing condition descriptors, and invokes methods on them
 */
public class ConditionDescriptors {

    private ArrayList<ConDescryptor> list= new ArrayList<>();

    /**
     * This method adds a descriptor to stored
     * @param conDescryptor descriptor to addition
     */
    public void addDescryptor(ConDescryptor conDescryptor){
        list.add(conDescryptor);
    }
    /**
     * This method removes a descriptor from stored
     * @param conDescryptor descriptor to removal
     */
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
    /**
     * This method asks all stored descriptors about conditionsT, when it comes to
     * descriptor which can describe given type it gets an object
     * @param conditionsT conditions list to describe
     * @return ArrayList<Condition> object described from @param
     */
    public ArrayList<Condition> descryptConditionT(List<ConditionT> conditionsT){
        ArrayList<Condition> conditionsList= new ArrayList<>();

        for (ConditionT conditionT : conditionsT) {
            conditionsList.add(askAllDescryptorsAbout(conditionT));
        }
        return conditionsList;
    }

}
