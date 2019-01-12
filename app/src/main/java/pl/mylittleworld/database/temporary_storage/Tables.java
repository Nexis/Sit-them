package pl.mylittleworld.database.temporary_storage;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import pl.mylittleworld.database.NameId;
import pl.mylittleworld.database.Storage;
import pl.mylittleworld.database.tables.ConditionT;
import pl.mylittleworld.database.tables.GroupT;
import pl.mylittleworld.database.tables.PersonT;
import pl.mylittleworld.database.tables.TableT;
import pl.mylittleworld.usadz_ich.genetics.SomethingWentTerriblyWrongException;

/**
 * This class is a temporary storage for tables list
 */
public class Tables implements Storage.GetDataListener {

    private static ArrayList<TableT> temporaryStorageTables=null;
    private static boolean initialized=false;

    /**
     * Gives  table list from temporary stored one
     * @return ArrayList of type TableT
     */
    public static ArrayList<TableT> getTemporaryStorageTables() {
        return temporaryStorageTables;
    }
    /**
     * Gives  tables list from temporary storaged one
     * @return ArrayList of type name id which is list of tables
     */
    public static ArrayList<NameId> getTemporaryStorageTablesAsNameId() {
        ArrayList<NameId> nameIdArrayList= new ArrayList<>();
        for(TableT tableT :temporaryStorageTables){
            NameId nameId= new NameId(tableT.getTableName(),tableT.getTableID());
            nameIdArrayList.add(nameId);
        }
        return nameIdArrayList;
    }
    /**
     * @param id is id of table
     * @return name of the table which id was given
     */
    public static String getNameOfTable(int id){
        for(TableT tableT:temporaryStorageTables){
            if(tableT.getTableID()==id){
                return tableT.getTableName();
            }
        }
        throw new NoSuchElementException("no such person with that id");
    }
    /**
     * @return if temporary storage has been initialized
     */
    public static boolean isInitialized(){
        return initialized;
    }
    /**
     * Provides posibility to initialize tables temporary storage
     * @param tableTS list of tables, values from this list will be used for initializing tables temporary storage
     */
    public static void initialize(ArrayList<TableT> tableTS){
        if(temporaryStorageTables==null) {
            temporaryStorageTables = tableTS;
            initialized = true;
        }
    }

    /**
     *
     * @param tableID
     * @return  table with given id
     */
    public static TableT getTable(int tableID){
        for(TableT tableT: temporaryStorageTables){
            if(tableT.getTableID()==tableID) {
                return tableT;
            }
        }
        throw new SomethingWentTerriblyWrongException("NO TABLE WITH GIVEN ID");
    }
    /**
     * Provides posibility to update tables temporary storage
     * @param tableTS list of tables, values from this list will be used for updating tables temporary storage
     *                 actual list is replaced with given one
     */
    public static void update(@NonNull ArrayList<TableT> tableTS){
        if(temporaryStorageTables!=null) {
            temporaryStorageTables = tableTS;
        }
    }


    @Override
    public void onGuestsListRetrived(ArrayList<PersonT> list) {

    }

    @Override
    public void onConditionsListRetrived(ArrayList<ConditionT> list) {

    }

    @Override
    public void onTablesListRetrived(ArrayList<TableT> list) {
        Tables.update(list);
    }

    @Override
    public void onListsRetrived(ArrayList<TableT> tableList, ArrayList<ConditionT> conditionsList, ArrayList<PersonT> peopleList) {

    }

    @Override
    public void onPersonGroupListsRetrived(List<GroupT> groups) {

    }
}