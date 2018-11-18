package pl.mylittleworld.usadz_ich.logic;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

import pl.mylittleworld.ThreadPoolExecutorForDatabaseAccess;
import pl.mylittleworld.database.People;
import pl.mylittleworld.database.Storage;
import pl.mylittleworld.database.tables.ChairT;
import pl.mylittleworld.database.tables.ConditionT;
import pl.mylittleworld.database.tables.PersonT;
import pl.mylittleworld.database.tables.TableT;
import pl.mylittleworld.usadz_ich.conditions.Condition;
import pl.mylittleworld.usadz_ich.view.AddConditionActivity;
import pl.mylittleworld.usadz_ich.view.ListViewActivity;
import pl.mylittleworld.usadz_ich.view.MainActivity;

import static android.app.Activity.RESULT_OK;

public class Control implements Storage.GetGuestsConditionsTablesListener {

    private Storage storageAssistant;


    public void giveStorageAssistant(Storage storage){
        storageAssistant=storage;
    }


    public void getPeopleListForDisplay(Storage.GetGuestsListener listener){
        storageAssistant.getGuestsList(listener);
    }

    public void userWantsToAddGuest(String personName){
        if(checkIfNameIsProper(personName)){
            storageAssistant.addGuest(personName);
        }
    }

    private boolean checkIfNameIsProper(String proposedName){

        for(int i=0;i<proposedName.length();++i){
            if(!Character.isLetter(proposedName.codePointAt(i))){
                return false;
            }
        }
        return true;

    }
    public void userWantsToDeleteGuests(PersonT... people){
        storageAssistant.deleteGuests(people);
    }
    public void userWantsToEditGuests(PersonT ... people){

    }

    public void userWantsToAddTables(int with,String tableName){
        storageAssistant.addTable(new TableT(with,tableName));

    }
    public void userWantsToDeleteTables(TableT ... tables){

    }

    public void userWantsToAddCondition(ConditionT... conditions){
        storageAssistant.addCondition(conditions);
    }
    public void userWantsToDeleteCondition(Condition... conditions){

    }

    public void getConditionsListForDisplay(Storage.GetConditionsListener listener) {
        storageAssistant.getConditionsList(listener);
    }

    public void userWantsToBrowseItems(TYPE_FOR_CONDITION type, Activity context) {

    }

    public void userChoseItem(int requestCode, int resultCode, Intent data, Activity activity) {
    }

    public void getTableListForDisplay(Storage.GetTablesListener listener) {
        storageAssistant.getTablesList(listener);
    }

    public void userWantsToCalculateSittingPlan() {
        storageAssistant.getPeopleConditionsAndTables(this);
    }

    @Override
    public void onListsRetrived(ArrayList<TableT> tableList, ArrayList<ConditionT> conditionsList, ArrayList<PersonT> peopleList) {
       ArrayList<ChairT> chairTS= new ArrayList<>();

       for(TableT table:tableList){
           for(int i=0,y=0;i<table.getTableWidth()*2;++i, y=(y+1)%2){

           }

       }
    }
}
