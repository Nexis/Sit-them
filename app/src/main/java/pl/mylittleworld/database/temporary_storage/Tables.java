package pl.mylittleworld.database.temporary_storage;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import pl.mylittleworld.database.NameId;
import pl.mylittleworld.database.tables.TableT;

public class Tables {

    private static ArrayList<TableT> temporaryStorageTables=null;
    private static boolean initialized=false;

    public static ArrayList<TableT> getTemporaryStorageTables() {
        return temporaryStorageTables;
    }
    public static ArrayList<NameId> getTemporaryStorageTablesAsNameId() {
        ArrayList<NameId> nameIdArrayList= new ArrayList<>();
        for(TableT tableT :temporaryStorageTables){
            NameId nameId= new NameId(tableT.getTableName(),tableT.getTableID());
            nameIdArrayList.add(nameId);
        }
        return nameIdArrayList;
    }
    public static String getNameOfTable(int id){
        for(TableT tableT:temporaryStorageTables){
            if(tableT.getTableID()==id){
                return tableT.getTableName();
            }
        }
        throw new NoSuchElementException("no such person with that id");
    }

    public static boolean isInitialized(){
        return initialized;
    }

    public static void initialize(ArrayList<TableT> tableTS){
        if(temporaryStorageTables==null) {
            temporaryStorageTables = tableTS;
            initialized = true;
        }
    }
    public static void update(@NonNull ArrayList<TableT> tableTS){
        if(temporaryStorageTables!=null) {
            temporaryStorageTables = tableTS;
        }
    }
}