package pl.mylittleworld.usadz_ich.json_service;

import com.google.gson.Gson;

import java.util.ArrayList;

import pl.mylittleworld.database.Storage;
import pl.mylittleworld.database.tables.ConditionT;
import pl.mylittleworld.database.tables.PersonT;
import pl.mylittleworld.database.tables.TableT;
import pl.mylittleworld.usadz_ich.SittingPlan;
import pl.mylittleworld.usadz_ich.logic.ControlProvider;

public class Json_service implements Storage.GetAllListener{

    void sth() {
        ControlProvider.getInstance().getAllForExport(this);

        Gson gson = new Gson();
       // String json = gson.toJson(obj);
    }

    @Override
    public void onListsRetrived(ArrayList<TableT> tableList, ArrayList<ConditionT> conditionsList, ArrayList<PersonT> peopleList, SittingPlan sittingPlan) {

    }
}
