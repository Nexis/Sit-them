package pl.mylittleworld.sit_them;

import java.util.ArrayList;

import pl.mylittleworld.sit_them.conditions.Condition;
import pl.mylittleworld.sit_them.model.Person;

public interface Storage {

    interface GetGuestsListener{

        public void onGuestsListRetrived(ArrayList<Person> list);
    }
    interface GetConditionsListener{

        public void onConditionsListRetrived(ArrayList<Condition> list);
    }

    public void getGuestsList(GetGuestsListener getGuestsListener);

    public void getConditionsList(GetConditionsListener getConditionsListener);

    public void addGuest(String name);

    public void deleteGuests(Person[] people);
}
