package pl.mylittleworld.database.temporary_storage;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import pl.mylittleworld.database.NameId;
import pl.mylittleworld.database.tables.TableT;

/**
 * This class is a temporary storage for tables list
 */
public class Tables {

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
     * Provides posibility to update tables temporary storage
     * @param tableTS list of tables, values from this list will be used for updating tables temporary storage
     *                 actual list is replaced with given one
     */
    public static void update(@NonNull ArrayList<TableT> tableTS){
        if(temporaryStorageTables!=null) {
            temporaryStorageTables = tableTS;
        }
    }
}