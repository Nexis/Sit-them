package pl.mylittleworld.usadz_ich.conditions.conditions_descriptors;

import android.support.annotation.Nullable;

import java.util.NoSuchElementException;

import pl.mylittleworld.database.temporary_storage.People;
import pl.mylittleworld.database.tables.ConditionT;
import pl.mylittleworld.database.tables.PersonT;
import pl.mylittleworld.usadz_ich.conditions.CONDITIONS_OPTIONS;
import pl.mylittleworld.usadz_ich.conditions.Condition;
import pl.mylittleworld.usadz_ich.conditions.MustNextToCondition;

/**
 * This is descriptor class for MustNextToCondition class
 */
public class ConMustNextToDescriptor implements ConDescryptor{

    /**
     * It describes conditionT creating a MustNextToCondition object if types are compatible
     * @param conditionT condition to describe
     * @param <T> MustNextToCondition
     * @return MustNextToCondition object with values from conditionT or null
     */
    @Nullable
    @Override
    public <T extends Condition> T constructConditionObject(ConditionT conditionT) {
        if(conditionT.getConditionType()==CONDITIONS_OPTIONS.MUST_NEXT_TO){
           PersonT p1= findPersonWithId(conditionT.getId1());
           PersonT p2= findPersonWithId(conditionT.getId2());
           int priority=conditionT.getPriority();
            return (T) new MustNextToCondition(p1,p2,conditionT.getConditionID(),priority);
        }
        else {
            return null;
        }
    }

    private PersonT findPersonWithId(int id){
        for(PersonT personT:People.getTemporaryStoragePeople()){
            if(personT.getPersonID()==id){
                return personT;
            }
        }
        throw new NoSuchElementException("No person with such id");
    }
}
