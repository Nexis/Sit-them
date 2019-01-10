package pl.mylittleworld.database;

import java.util.ArrayList;

import pl.mylittleworld.database.tables.ConditionT;
import pl.mylittleworld.database.tables.PersonT;
import pl.mylittleworld.database.tables.TableT;
import pl.mylittleworld.database.tables.TablesPlanT;
import pl.mylittleworld.usadz_ich.json_service.ImportDataListener;
import pl.mylittleworld.usadz_ich.json_service.Json_format;

/**
 * interface which defines storage access functions which Controler can invoke and which model part should implements
 */
public interface Storage {

    void addCondition(ConditionT... conditionT);

    void deleteTable(int id);

    void deleteCondition(int conditionId);

    interface GetDataListener {

        void onGuestsListRetrived(ArrayList<PersonT> list);

        void onConditionsListRetrived(ArrayList<ConditionT> list);

        void onTablesListRetrived(ArrayList<TableT> list);

        void onListsRetrived(ArrayList<TableT> tableList,ArrayList<ConditionT> conditionsList,ArrayList<PersonT> peopleList);

    }



    void getGuestsList(GetDataListener getDataListener);

    void getConditionsList(GetDataListener getConditionsListener);

    void addGuest(String name);

    void deleteGuests(PersonT person);

    void addTablesPlan(TablesPlanT tablesPlanT);

    void deleteTablesPlan(TablesPlanT tablesPlanT);

    void addTable(TableT tableT);

    void getTablesList(GetDataListener listener);

    void getPeopleConditionsAndTables(GetDataListener listener);

    void cleanAndImportData(ImportDataListener listener, Json_format json_format);
}
