package pl.mylittleworld.usadz_ich.logic;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;

import pl.mylittleworld.ThreadPoolExecutorForDatabaseAccess;
import pl.mylittleworld.database.People;
import pl.mylittleworld.database.Storage;
import pl.mylittleworld.database.tables.ChairT;
import pl.mylittleworld.database.tables.ConditionT;
import pl.mylittleworld.database.tables.PersonT;
import pl.mylittleworld.database.tables.TableT;
import pl.mylittleworld.usadz_ich.SittingPlan;
import pl.mylittleworld.usadz_ich.conditions.Condition;
import pl.mylittleworld.usadz_ich.conditions.Conditions;
import pl.mylittleworld.usadz_ich.conditions.conditions_descriptors.ConCanTNextToDescriptor;
import pl.mylittleworld.usadz_ich.conditions.conditions_descriptors.ConMustAtTableDescriptor;
import pl.mylittleworld.usadz_ich.conditions.conditions_descriptors.ConMustNextToDescriptor;
import pl.mylittleworld.usadz_ich.conditions.conditions_descriptors.ConditionDescriptors;
import pl.mylittleworld.usadz_ich.genetics.GeneticAlgorithms;
import pl.mylittleworld.usadz_ich.view.AddConditionActivity;
import pl.mylittleworld.usadz_ich.view.ListViewActivity;
import pl.mylittleworld.usadz_ich.view.MainActivity;

import static android.app.Activity.RESULT_OK;

public class Control {

    private Storage storageAssistant;
    private ConditionDescriptors conditionDescriptors;


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

    public void userWantsToCalculateSittingPlan(Storage.GetGuestsConditionsTablesListener storage) {
        storageAssistant.getPeopleConditionsAndTables(storage);
    }

    private  void addChairsForTable(TableT table, ArrayList<ChairT> chairTS){

            int width=table.getTableWidth();
            for(int i=0,y=0;i<width*2;++i){
                if(i<width){
                    y=0;
                }
                else {
                    y=1;
                }

                chairTS.add(new ChairT(table.getTableID(),i%width,y));
            }

    }


    private void addConDescriptors(){
        conditionDescriptors.addDescryptor(new ConMustNextToDescriptor());
        conditionDescriptors.addDescryptor(new ConCanTNextToDescriptor());
        conditionDescriptors.addDescryptor(new ConMustAtTableDescriptor());
    }

    public ConditionDescriptors getConditionDescriptor() {
        if(conditionDescriptors==null){
            conditionDescriptors= new ConditionDescriptors();
            addConDescriptors();
        }
        return conditionDescriptors;
    }

    public void getSittingPlan(ArrayList<TableT> tableList, ArrayList<ConditionT> conditionsList, ArrayList<PersonT> peopleList,MainActivity context) {
        ArrayList<ChairT> chairTS= new ArrayList<>();

        for(TableT table:tableList) {
            addChairsForTable(table,chairTS);
        }

        ArrayList<Condition> conditions=conditionDescriptors.descryptConditionT(conditionsList);

        GeneticAlgorithms geneticAlgorithms=new GeneticAlgorithms(chairTS,peopleList,new Conditions(conditions));
        SittingPlan sittingPlan=geneticAlgorithms.evolution();

        context.showSittingPlanList(sittingPlan,tableList);
    }
}
