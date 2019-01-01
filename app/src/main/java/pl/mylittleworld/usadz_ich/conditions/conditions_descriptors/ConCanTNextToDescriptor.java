package pl.mylittleworld.usadz_ich.conditions.conditions_descriptors;

import android.support.annotation.Nullable;

import java.util.NoSuchElementException;

import pl.mylittleworld.database.temporary_storage.People;
import pl.mylittleworld.database.tables.ConditionT;
import pl.mylittleworld.database.tables.PersonT;
import pl.mylittleworld.usadz_ich.conditions.CONDITIONS_OPTIONS;
import pl.mylittleworld.usadz_ich.conditions.CantNextToCondition;
import pl.mylittleworld.usadz_ich.conditions.Condition;

public class ConCanTNextToDescriptor implements ConDescryptor{

    @Nullable
    @Override
    public <T extends Condition> T constructConditionObject(ConditionT conditionT) {
        if(conditionT.getConditionType()==CONDITIONS_OPTIONS.CAN_T_NEXT_TO){
            PersonT p1= findPersonWithId(conditionT.getId1());
            PersonT p2= findPersonWithId(conditionT.getId2());
            return (T) new CantNextToCondition(p1,p2,conditionT.getConditionID());
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

