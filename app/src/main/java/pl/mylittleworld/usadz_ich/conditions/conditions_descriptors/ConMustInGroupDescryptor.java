package pl.mylittleworld.usadz_ich.conditions.conditions_descriptors;

import android.support.annotation.Nullable;

import java.util.NoSuchElementException;

import pl.mylittleworld.database.tables.ConditionT;
import pl.mylittleworld.database.tables.GroupT;
import pl.mylittleworld.database.tables.PersonT;
import pl.mylittleworld.database.temporary_storage.Groups;
import pl.mylittleworld.database.temporary_storage.People;
import pl.mylittleworld.usadz_ich.conditions.CONDITIONS_OPTIONS;
import pl.mylittleworld.usadz_ich.conditions.Condition;
import pl.mylittleworld.usadz_ich.conditions.MustInGroupCondition;

/**
 * This is descriptor class for CantNextToCondition class
 */
public class ConMustInGroupDescryptor implements ConDescryptor{

        /**
         * It describes conditionT creating a MustInGroupCondition object if types are compatible
         * @param conditionT condition to describe
         * @param <T> MustInGroupCondition
         * @return MustInGroupCondition object with values from conditionT or null
         */
        @Nullable
        @Override
        public <T extends Condition> T constructConditionObject(ConditionT conditionT) {
            if(conditionT.getConditionType()==CONDITIONS_OPTIONS.MUST_IN_GROUP){
                PersonT p1= findPersonWithId(conditionT.getId1());
                GroupT g= findGroupWithId(conditionT.getId2());
                int priority= conditionT.getPriority();
                return (T) new MustInGroupCondition(p1,g,conditionT.getConditionID(),priority);
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
    private GroupT findGroupWithId(int id){
        for(GroupT groupT:Groups.getTemporaryStorageGroups()){
            if(groupT.getGroupID()==id){
                return groupT;
            }
        }
        throw new NoSuchElementException("No group with such id");
    }


}
