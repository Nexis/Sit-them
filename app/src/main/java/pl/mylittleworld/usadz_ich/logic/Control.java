package pl.mylittleworld.usadz_ich.logic;


import android.widget.Toast;

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
import pl.mylittleworld.usadz_ich.TABLE_TYPE;
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

/**
 * This class knows who to ask about data and how to react on users actions
 */
public class Control {

    private Storage storageAssistant;
    private ConditionDescriptors conditionDescriptors;

    /**
     * @param storage it s given for storage access assistant
     */
    public void giveStorageAssistant(Storage storage){
        storageAssistant=storage;
    }

    /**
     * It's invoked when user goes into Guests list view
     * @param listener which should be called when guests list data is ready
     */
    public void getPeopleListForDisplay(Storage.GetDataListener listener){
        storageAssistant.getGuestsList(listener);
    }
    public void getAllForExport(Storage.GetDataListener listener){
        storageAssistant.getPeopleConditionsAndTables(listener);
    }
    public void userWantsToImportDataFromJson(ImportDataListener listener, Json_format json){
        storageAssistant.cleanAndImportData(listener,json);
    }

    /**
     * It's invoked when user wants add guest
     * @param personName name of person to add
     */
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
    /**
     * It's invoked when user wants delete guests
     * @param people list pf guests which user wants to delete
     */
    public void userWantsToDeleteGuests(PersonT people){
        storageAssistant.deleteGuests(people);
        TemporaryStorageSittingPlan.setActual(false);
    }


    /**
     * It's invoked when user wants add tables
     * @param with of new table
     * @param tableName name of table
     * @param tableType type of table
     */
    public void userWantsToAddTables(int with, String tableName, TABLE_TYPE tableType){
        storageAssistant.addTable(new TableT(with,tableName,tableType));
        TemporaryStorageSittingPlan.setActual(false);

    }

    /**
     * It's invoked when user wants add condition
     * @param conditions list of conditions to add
     */
    public void userWantsToAddCondition(ConditionT... conditions){
        storageAssistant.addCondition(conditions);
        TemporaryStorageSittingPlan.setActual(false);
    }
    /**
     * It's invoked when user wants delete condition
     * @param conditionId id of condition which user wants to delete
     */
    public void userWantsToDeleteCondition(int conditionId){
        storageAssistant.deleteCondition(conditionId);
        TemporaryStorageSittingPlan.setActual(false);

    }
    /**
     * It's invoked when user goes into Condition list view
     * @param listener which should be called when conditions list data is ready
     */
    public void getConditionsListForDisplay(Storage.GetDataListener listener) {
        storageAssistant.getConditionsList(listener);
    }

    /**
     * It's invoked when user goes into Tables list view
     * @param listener which should be called when tables list data is ready
     */
    public void getTableListForDisplay(Storage.GetDataListener listener) {
        storageAssistant.getTablesList(listener);
    }

    /**
     * It's invoked when user goes into Sitting plan view and wants a new one to be generated
     * @param storage listener which should be called when all needed is ready
     */
    public void userWantsToCalculateSittingPlan(Storage.GetDataListener storage) {
        storageAssistant.getPeopleConditionsAndTables(storage);
    }
    /**
     * It's invoked when user goes into Sitting plan view
     * @param context which should be called when sitting plan data is ready
     */
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

    /**
     * This method invokes generating new sitting plan
     * @param tableList data which will be used to generate proper sitting plan
     * @param conditionsList data which will be used to generate proper sitting plan
     * @param peopleList data which will be used to generate proper sitting plan
     * @param context listener to inform and give generated sitting plan
     */
    public void getSittingPlan(ArrayList<TableT> tableList, ArrayList<ConditionT> conditionsList, final ArrayList<PersonT> peopleList,final MainActivity context) {
        final ArrayList<ChairT> chairTS= new ArrayList<>();



        for(TableT table:tableList) {
            addChairsForTable(table,chairTS);
        }
        if(peopleList.size()!=chairTS.size()){
            if (context.isFinishing() || context.isDestroyed()) return;
            context.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (context.isFinishing() || context.isDestroyed()) return;
                    Toast.makeText(context,"ILOŚĆ MIEJSC AKTUALNIE "+ chairTS.size()+" I ILOŚĆ OSÓB AKTUALNIE " +peopleList.size() +" MUSZĄ BYĆ TAKIE SAME",Toast.LENGTH_LONG).show();
                }
            });
           return;
        }

        ArrayList<Condition> conditions=conditionDescriptors.descryptConditionT(conditionsList);

        GeneticAlgorithms geneticAlgorithms=new GeneticAlgorithms(chairTS,peopleList,new Conditions(conditions));
        SittingPlan sittingPlan=geneticAlgorithms.evolution();

        TemporaryStorageSittingPlan.insertActualSittingPlan(sittingPlan);
        context.showSittingPlanList(sittingPlan,tableList);
    }

    /**
     * This function is invoked when item from universal list
     * @param id of thing which will be delated
     * @param dataType type of list
     */
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
