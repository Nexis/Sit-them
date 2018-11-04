package pl.mylittleworld.database;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import pl.mylittleworld.ThreadPoolExecutorForDatabaseAccess;
import pl.mylittleworld.database.tables.ConditionT;
import pl.mylittleworld.database.tables.PersonT;
import pl.mylittleworld.database.tasks.GetConditionsTask;

public class StorageAssistant implements Storage {

    private DataBaseClass dataBase;

    public StorageAssistant(Context context) {

        dataBase= Room.databaseBuilder(context,DataBaseClass.class,"sit_database").build();
    }

    @Override
    public void getGuestsList(final GetGuestsListener getGuestsListener) {
        ThreadPoolExecutorForDatabaseAccess.getExecutor().submit(new Runnable() {
            @Override
            public void run() {
               ArrayList<PersonT> people= new ArrayList<>(dataBase.getDao().getGuests());
                getGuestsListener.onGuestsListRetrived(people);
            }
        });
    }

    @Override
    public void getConditionsList(final GetConditionsListener getConditionsListener) {
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


}
