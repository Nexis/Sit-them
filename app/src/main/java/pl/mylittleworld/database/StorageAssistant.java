package pl.mylittleworld.database;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import pl.mylittleworld.ThreadPoolExecutorForDatabaseAccess;
import pl.mylittleworld.database.tasks.GetGuestsTask;
import pl.mylittleworld.sit_them.Storage;
import pl.mylittleworld.sit_them.model.Person;

public class StorageAssistant implements Storage {

    private DataBaseClass dataBase;

    public StorageAssistant(Context context) {

        dataBase= Room.databaseBuilder(context,DataBaseClass.class,"sit_database").build();
    }

    @Override
    public void getGuestsList(GetGuestsListener getGuestsListener) {
        ThreadPoolExecutorForDatabaseAccess.getExecutor().submit(new GetGuestsTask(getGuestsListener,dataBase.getDao()));
    }

    @Override
    public void getConditionsList(GetConditionsListener getConditionsListener) {
        ThreadPoolExecutorForDatabaseAccess.getExecutor().submit(new Runnable() {
            @Override
            public void run() {
                dataBase.getDao().getConditionsList();
            }
        });
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
    public void deleteGuests(final Person... people) {
        ThreadPoolExecutorForDatabaseAccess.getExecutor().submit(new Runnable() {
            @Override
            public void run() {
                PersonT[] peopleT= new PersonT[people.length];

                for(int i=0;i<people.length;++i){
                    peopleT[i]=new PersonT(people[i].getName(),people[i].getPersonID());
                }

                dataBase.getDao().deletePerson(peopleT);
            }
        });
    }


}
