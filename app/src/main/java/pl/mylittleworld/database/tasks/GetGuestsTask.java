package pl.mylittleworld.database.tasks;

import android.database.Cursor;

import java.util.ArrayList;

import pl.mylittleworld.database.DAOClass;
import pl.mylittleworld.sit_them.Storage;
import pl.mylittleworld.sit_them.model.Person;

public class GetGuestsTask implements Runnable {

    private Storage.GetGuestsListener getGuestsListener;
    private DAOClass dao;

    public GetGuestsTask(Storage.GetGuestsListener listener,DAOClass dao) {
        this.getGuestsListener=listener;
        this.dao=dao;
    }

    @Override
    public void run() {
      ArrayList<Person> people= new ArrayList<>();
        Cursor peopleCursor=dao.getGuests();

        peopleCursor.moveToFirst();

        while(peopleCursor.moveToNext()){
            String name=peopleCursor.getString(peopleCursor.getColumnIndex("name"));
            int id= peopleCursor.getInt(peopleCursor.getColumnIndex("personID"));
            Person person= new Person(id,name);
            people.add(person);
        }

        people.add(new Person(1,"Kinga Poszytek"));
        getGuestsListener.onGuestsListRetrived(people);

    }
}
