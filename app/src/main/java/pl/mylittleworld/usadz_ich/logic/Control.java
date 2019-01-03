package pl.mylittleworld.usadz_ich.logic;


import android.app.Activity;
import android.content.Intent;

import java.util.ArrayList;

import pl.mylittleworld.database.Storage;
import pl.mylittleworld.database.tables.ChairT;
import pl.mylittleworld.database.tables.ConditionT;
import pl.mylittleworld.database.tables.PersonT;
import pl.mylittleworld.database.tables.TableT;
import pl.mylittleworld.database.temporary_storage.Tables;
import pl.mylittleworld.database.temporary_storage.TemporaryStorageSittingPlan;
import pl.mylittleworld.usadz_ich.DATA_TYPE;
import pl.mylittleworld.usadz_ich.SittingPlan;
import pl.mylittleworld.usadz_ich.conditions.Condition;
import pl.mylittleworld.usadz_ich.conditions.Conditions;
import pl.mylittleworld.usadz_ich.conditions.conditions_descriptors.ConCanTNextToDescriptor;
import pl.mylittleworld.usadz_ich.conditions.conditions_descriptors.ConMustAtTableDescriptor;
import pl.mylittleworld.usadz_ich.conditions.conditions_descriptors.ConMustNextToDescriptor;
import pl.mylittleworld.usadz_ich.conditions.conditions_descriptors.ConditionDescriptors;
import pl.mylittleworld.usadz_ich.genetics.GeneticAlgorithms;
import pl.mylittleworld.usadz_ich.json_service.ImportDataListener;
import pl.mylittleworld.usadz_ich.json_service.Json_format;
import pl.mylittleworld.usadz_ich.view.MainActivity;

public class Control {

    private Storage storageAssistant;
    private ConditionDescriptors conditionDescriptors;


    public void giveStorageAssistant(Storage storage){
        storageAssistant=storage;
    }


    public void getPeopleListForDisplay(Storage.GetDataListener listener){
        storageAssistant.getGuestsList(listener);
    }
    public void getAllForExport(Storage.GetDataListener listener){
        storageAssistant.getPeopleConditionsAndTables(listener);
    }
    public void userWantsToImportDataFromJson(ImportDataListener listener, Json_format json){
        storageAssistant.cleanAndImportData(listener,json);

    }

    public void userWantsToAddGuest(String personName){
        if(checkIfNameIsProper(personName)){
            storageAssistant.addGuest(personName);
            TemporaryStorageSittingPlan.setActual(false);
        }
    }

    private boolean checkIfNameIsProper(String proposedName){

        for(int i=0;i<proposedName.length();++i){
            if(!Character.isLetter(proposedName.codePointAt(i))&& proposedName.charAt(i)!=' '){
                return false;
            }
        }
        return true;

    }
    public void userWantsToDeleteGuests(PersonT... people){
        storageAssistant.deleteGuests(people);
        TemporaryStorageSittingPlan.setActual(false);
    }


    public void userWantsToAddTables(int with,String tableName){
        storageAssistant.addTable(new TableT(with,tableName));
        TemporaryStorageSittingPlan.setActual(false);

    }
    public void userWantsToDeleteTables(TableT ... tables){
        TemporaryStorageSittingPlan.setActual(false);
    }

    public void userWantsToAddCondition(ConditionT... conditions){
        storageAssistant.addCondition(conditions);
        TemporaryStorageSittingPlan.setActual(false);
    }
    public void userWantsToDeleteCondition(int conditionId){
        storageAssistant.deleteCondition(conditionId);
        TemporaryStorageSittingPlan.setActual(false);

    }

    public void getConditionsListForDisplay(Storage.GetDataListener listener) {
        storageAssistant.getConditionsList(listener);
    }

    public void userWantsToBrowseItems(TYPE_FOR_CONDITION type, Activity context) {

    }

    public void userChoseItem(int requestCode, int resultCode, Intent data, Activity activity) {
    }

    public void getTableListForDisplay(Storage.GetDataListener listener) {
        storageAssistant.getTablesList(listener);
    }

    public void userWantsToCalculateSittingPlan(Storage.GetDataListener storage) {
        storageAssistant.getPeopleConditionsAndTables(storage);
    }
    public void userWantToSeeCurrentSittingPlan(MainActivity context){
        if(TemporaryStorageSittingPlan.exists() && TemporaryStorageSittingPlan.isActual() && Tables.isInitialized()){
            SittingPlan sittingPlan=TemporaryStorageSittingPlan.getActualSittingPlan();
            context.showSittingPlanList(sittingPlan,Tables.getTemporaryStorageTables());
        }
        else{
            context.noContent();
        }
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

        TemporaryStorageSittingPlan.insertActualSittingPlan(sittingPlan);
        context.showSittingPlanList(sittingPlan,tableList);
    }

    public void userWantsToDeleteItem(int id, DATA_TYPE dataType) {
        if(dataType!=null){
            switch (dataType){
                case TABLES:
                    storageAssistant.deleteTable(id);
                    TemporaryStorageSittingPlan.setActual(false);
                    break;
            }
        }
    }


}
