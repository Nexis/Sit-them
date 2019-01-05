package pl.mylittleworld.usadz_ich.conditions.conditions_descriptors;

import android.support.annotation.Nullable;

import java.util.NoSuchElementException;

import pl.mylittleworld.database.temporary_storage.People;
import pl.mylittleworld.database.temporary_storage.Tables;
import pl.mylittleworld.database.tables.ConditionT;
import pl.mylittleworld.database.tables.PersonT;
import pl.mylittleworld.database.tables.TableT;
import pl.mylittleworld.usadz_ich.conditions.CONDITIONS_OPTIONS;
import pl.mylittleworld.usadz_ich.conditions.Condition;
import pl.mylittleworld.usadz_ich.conditions.MustAtTableCondition;

public class ConMustAtTableDescriptor implements ConDescryptor{

    @Nullable
    @Override
    public <T extends Condition> T constructConditionObject(ConditionT conditionT) {
        if(conditionT.getConditionType()==CONDITIONS_OPTIONS.MUST_AT_TABLE){
            PersonT p= findPersonWithId(conditionT.getId1());
            TableT t= findTableWithId(conditionT.getId2());
            int priority=conditionT.getPriority();
            return (T) new MustAtTableCondition(p,t,conditionT.getConditionID(),priority);
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
    private TableT findTableWithId(int id){
        for(TableT tableT:Tables.getTemporaryStorageTables()){
            if(tableT.getTableID()==id){
                return tableT;
            }
        }
        throw new NoSuchElementException("No table with such id");
    }

}