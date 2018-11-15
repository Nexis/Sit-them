package pl.mylittleworld.database;

import java.util.ArrayList;

import pl.mylittleworld.database.tables.ConditionT;
import pl.mylittleworld.database.tables.PersonT;
import pl.mylittleworld.usadz_ich.conditions.Condition;

public interface Storage {

    void addCondition(ConditionT... conditionT);

    interface GetGuestsListener{

        public void onGuestsListRetrived(ArrayList<PersonT> list);
    }
    interface GetConditionsListener{

        public void onConditionsListRetrived(ArrayList<ConditionT> list);
    }

    public void getGuestsList(GetGuestsListener getGuestsListener);

    public void getConditionsList(GetConditionsListener getConditionsListener);

    public void addGuest(String name);

    public void deleteGuests(PersonT[] people);
}
