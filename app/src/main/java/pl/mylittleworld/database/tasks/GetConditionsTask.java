package pl.mylittleworld.database.tasks;

import java.util.ArrayList;

import pl.mylittleworld.database.DAOClass;
import pl.mylittleworld.database.Storage;
import pl.mylittleworld.database.tables.ConditionT;
import pl.mylittleworld.database.tables.PersonT;

public class GetConditionsTask implements Runnable {

    private DAOClass dao;
    private Storage.GetConditionsListener getConditionsListener;


    public GetConditionsTask(DAOClass dao, Storage.GetConditionsListener getConditionsListener) {
        this.dao = dao;
        this.getConditionsListener = getConditionsListener;
    }

    @Override
    public void run() {
        ArrayList<ConditionT> conditions= new ArrayList<>(dao.getConditionsList());

        getConditionsListener.onConditionsListRetrived(conditions);
    }
}
