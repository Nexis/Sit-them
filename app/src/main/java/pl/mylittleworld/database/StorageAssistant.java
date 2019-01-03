package pl.mylittleworld.database;

import android.arch.persistence.room.Room;
import android.content.Context;

import java.util.ArrayList;

import pl.mylittleworld.database.tables.ConditionT;
import pl.mylittleworld.database.tables.PersonT;
import pl.mylittleworld.database.tables.TableT;
import pl.mylittleworld.database.tables.TablesPlanT;
import pl.mylittleworld.database.tasks.GetConditionsTask;
import pl.mylittleworld.database.temporary_storage.TemporaryStorageSittingPlan;
import pl.mylittleworld.usadz_ich.json_service.ImportDataListener;
import pl.mylittleworld.usadz_ich.json_service.Json_format;

public class StorageAssistant implements Storage {

    private DataBaseClass dataBase;

    public StorageAssistant(Context context) {

        dataBase= Room.databaseBuilder(context,DataBaseClass.class,"sit_database").fallbackToDestructiveMigration().build();
    }

    @Override
    public void addCondition(final ConditionT... conditionT) {
        ThreadPoolExecutorForDatabaseAccess.getExecutor().submit(new Runnable() {
            @Override
            public void run() {
                dataBase.getDao().addConditions(conditionT);
            }
        });
    }

    @Override
    public void deleteTable(final int id) {
        ThreadPoolExecutorForDatabaseAccess.getExecutor().submit(new Runnable() {
            @Override
            public void run() {
                dataBase.getDao().deleteTableWithId(id);
            }
        });
    }

    @Override
    public void deleteCondition(final int conditionId) {
        ThreadPoolExecutorForDatabaseAccess.getExecutor().submit(new Runnable() {
            @Override
            public void run() {
                dataBase.getDao().deleteConditionWithId(conditionId);
            }
        });
    }

    @Override
    public void getGuestsList(final GetDataListener getDataListener) {
        ThreadPoolExecutorForDatabaseAccess.getExecutor().submit(new Runnable() {
            @Override
            public void run() {
               ArrayList<PersonT> people= new ArrayList<>(dataBase.getDao().getGuests());
                getDataListener.onGuestsListRetrived(people);
            }
        });
    }

    @Override
    public void getConditionsList(final GetDataListener getConditionsListener) {
        ThreadPoolExecutorForDatabaseAccess.getExecutor().submit(new GetConditionsTask(dataBase.getDao(),getConditionsListener));
    }

    @Override
    public void addGuest(final String name) {
        ThreadPoolExecutorForDatabaseAccess.getExecutor().submit(new Runnable() {
            @Override
            public void run() {
                dataBase.getDao().insertPerson(new PersonT(name));
            }
        });
    }

    @Override
    public void deleteGuests(final PersonT... peopleT) {
        ThreadPoolExecutorForDatabaseAccess.getExecutor().submit(new Runnable() {
            @Override
            public void run() {
                dataBase.getDao().deletePerson(peopleT);
            }
        });
    }

    @Override
    public void addTablesPlan(TablesPlanT tablesPlanT) {

    }

    @Override
    public void deleteTablesPlan(TablesPlanT tablesPlanT) {

    }

    @Override
    public void addTable(final TableT tableT){
       ThreadPoolExecutorForDatabaseAccess.getExecutor().submit(new Runnable() {
           @Override
           public void run() {
               dataBase.getDao().insertTable(tableT);
           }
       });
    }

    @Override
    public void getTablesList(final GetDataListener listener) {
        ThreadPoolExecutorForDatabaseAccess.getExecutor().submit(new Runnable() {
            @Override
            public void run() {
               ArrayList<TableT> tables= new ArrayList<>(dataBase.getDao().getTablesList());
                listener.onTablesListRetrived(tables);
            }
        });
    }

    @Override
    public void getPeopleConditionsAndTables(final Storage.GetDataListener listener){
        ThreadPoolExecutorForDatabaseAccess.getExecutor().submit(new Runnable() {
            @Override
            public void run() {
                ArrayList<TableT> tables= new ArrayList<>(dataBase.getDao().getTablesList());
                ArrayList<PersonT> people= new ArrayList<>(dataBase.getDao().getGuests());
                ArrayList<ConditionT> conditions= new ArrayList<>(dataBase.getDao().getConditionsList());
                listener.onListsRetrived(tables,conditions,people);
            }
        });
    }


    @Override
    public void cleanAndImportData(final ImportDataListener listener, final Json_format json_format) {
        ThreadPoolExecutorForDatabaseAccess.getExecutor().submit(new Runnable() {
            @Override
            public void run() {
               dataBase.getDao().dropConditions();
               dataBase.getDao().dropPeople();
               dataBase.getDao().dropTables();

               dataBase.getDao().insertTable(json_format.getTableList().toArray(new TableT[json_format.getTableList().size()]));
               dataBase.getDao().insertPerson(json_format.getPeopleList().toArray(new PersonT[json_format.getPeopleList().size()]));
               dataBase.getDao().addConditions(json_format.getConditionsList().toArray(new ConditionT[json_format.getConditionsList().size()]));

                TemporaryStorageSittingPlan.insertActualSittingPlan(json_format.getSittingPlan());

                listener.onDataImported();
            }
        });
    }
}
