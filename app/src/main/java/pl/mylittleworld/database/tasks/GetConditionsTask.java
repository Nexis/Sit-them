package pl.mylittleworld.database.tasks;

import android.database.Cursor;

import java.util.ArrayList;

import pl.mylittleworld.database.ConditionTypesConverter;
import pl.mylittleworld.database.DAOClass;
import pl.mylittleworld.sit_them.Storage;
import pl.mylittleworld.sit_them.conditions.CONDITIONS_OPTIONS;
import pl.mylittleworld.sit_them.conditions.Condition;
import pl.mylittleworld.sit_them.model.Person;

public class GetConditionsTask implements Runnable {

        private Storage.GetConditionsListener getConditionsListener;
        private DAOClass dao;

        public GetConditionsTask(Storage.GetConditionsListener getConditionsListener,DAOClass dao) {
            this.getConditionsListener=getConditionsListener;
            this.dao=dao;
        }

        @Override
        public void run() {
            ArrayList<Condition> conditions= new ArrayList<>();
          /*  Cursor conditionsCursor=dao.getConditionsList();

            conditionsCursor.moveToFirst();

            while(conditionsCursor.moveToNext()){
                CONDITIONS_OPTIONS conditions_option= ConditionTypesConverter.stringToCondition(conditionsCursor.getString(conditionsCursor.getColumnIndex("conditionType")));
                int id1= conditionsCursor.getInt(conditionsCursor.getColumnIndex("id1"));
                int id2= conditionsCursor.getInt(conditionsCursor.getColumnIndex("id2"));
              //  int conditionID

              //  Person person= new Person(id,name);
              //  conditions.add(person);
            }

           // conditions.add(new Person(1,"Kinga Poszytek"));
            getConditionsListener.onConditionsListRetrived(conditions);*/

        }

}
