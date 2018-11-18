package pl.mylittleworld.database;

import java.util.ArrayList;

import pl.mylittleworld.database.tables.ConditionT;
import pl.mylittleworld.database.tables.PersonT;
import pl.mylittleworld.database.tables.TableT;
import pl.mylittleworld.database.tables.TablesPlanT;
import pl.mylittleworld.usadz_ich.conditions.Condition;

public interface Storage {

    void addCondition(ConditionT... conditionT);

    interface GetGuestsListener{

        void onGuestsListRetrived(ArrayList<PersonT> list);
    }
    interface GetConditionsListener{

        void onConditionsListRetrived(ArrayList<ConditionT> list);
    }

    void getGuestsList(GetGuestsListener getGuestsListener);

    void getConditionsList(GetConditionsListener getConditionsListener);

    void addGuest(String name);

    void deleteGuests(PersonT[] people);

    void addTablesPlan(TablesPlanT tablesPlanT);

    void deleteTablesPlan(TablesPlanT tablesPlanT);

    void addTable(TableT tableT);
}
