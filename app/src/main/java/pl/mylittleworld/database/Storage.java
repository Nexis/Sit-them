package pl.mylittleworld.database;

import java.util.ArrayList;

import pl.mylittleworld.database.tables.ConditionT;
import pl.mylittleworld.database.tables.PersonT;
import pl.mylittleworld.database.tables.TableT;
import pl.mylittleworld.database.tables.TablesPlanT;
import pl.mylittleworld.usadz_ich.SittingPlan;
import pl.mylittleworld.usadz_ich.conditions.Condition;
import pl.mylittleworld.usadz_ich.json_service.ImportDataListener;
import pl.mylittleworld.usadz_ich.json_service.Json_format;

public interface Storage {

    void addCondition(ConditionT... conditionT);

    void deleteTable(int id);

    void deleteCondition(int conditionId);

    interface GetGuestsListener{

        void onGuestsListRetrived(ArrayList<PersonT> list);
    }
    interface GetConditionsListener{

        void onConditionsListRetrived(ArrayList<ConditionT> list);
    }
    interface GetTablesListener{

        void onTablesListRetrived(ArrayList<TableT> list);
    }

    interface GetGuestsConditionsTablesListener{

        void onListsRetrived(ArrayList<TableT> tableList,ArrayList<ConditionT> conditionsList,ArrayList<PersonT> peopleList);
    }
    interface GetAllListener{
        void onListsRetrived(ArrayList<TableT> tableList, ArrayList<ConditionT> conditionsList, ArrayList<PersonT> peopleList);
    }


    void getGuestsList(GetGuestsListener getGuestsListener);

    void getConditionsList(GetConditionsListener getConditionsListener);

    void addGuest(String name);

    void deleteGuests(PersonT[] people);

    void addTablesPlan(TablesPlanT tablesPlanT);

    void deleteTablesPlan(TablesPlanT tablesPlanT);

    void addTable(TableT tableT);

    void getTablesList(GetTablesListener listener);

    void getPeopleConditionsAndTables(GetGuestsConditionsTablesListener listener);

    void cleanAndImportData(ImportDataListener listener,Json_format json_format);
}
