package pl.mylittleworld.usadz_ich.conditions.conditions_descriptors;

import android.support.annotation.Nullable;

import java.util.NoSuchElementException;

import pl.mylittleworld.database.People;
import pl.mylittleworld.database.tables.ConditionT;
import pl.mylittleworld.database.tables.PersonT;
import pl.mylittleworld.usadz_ich.conditions.CONDITIONS_OPTIONS;
import pl.mylittleworld.usadz_ich.conditions.Condition;
import pl.mylittleworld.usadz_ich.conditions.MustNextToCondition;

public class ConMustNextToDescriptor implements ConDescryptor{

    @Nullable
    @Override
    public <T extends Condition> T constructConditionObject(ConditionT conditionT) {
        if(conditionT.getConditionType()==CONDITIONS_OPTIONS.MUST_NEXT_TO){
           PersonT p1= findPersonWithId(conditionT.getId1());
           PersonT p2= findPersonWithId(conditionT.getId2());
            return (T) new MustNextToCondition(p1,p2,conditionT.getConditionID());
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
