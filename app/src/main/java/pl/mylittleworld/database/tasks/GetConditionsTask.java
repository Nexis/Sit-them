package pl.mylittleworld.database.tasks;

import java.util.ArrayList;

import pl.mylittleworld.database.DAOClass;
import pl.mylittleworld.database.Storage;
import pl.mylittleworld.database.tables.ConditionT;

/**
 * This class is a task for getting Conditions from database
 */
public class GetConditionsTask implements Runnable {

    private DAOClass dao;
    private Storage.GetDataListener getConditionsListener;


    public GetConditionsTask(DAOClass dao, Storage.GetDataListener getConditionsListener) {
        this.dao = dao;
        this.getConditionsListener = getConditionsListener;
    }

    @Override
    public void run() {
        ArrayList<ConditionT> conditions= new ArrayList<>(dao.getConditionsList());

        getConditionsListener.onConditionsListRetrived(conditions);
    }
}
