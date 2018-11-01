package pl.mylittleworld.sit_them.logic;


import java.util.ArrayList;

import pl.mylittleworld.sit_them.Storage;
import pl.mylittleworld.sit_them.model.People;
import pl.mylittleworld.sit_them.model.Person;
import pl.mylittleworld.sit_them.model.Table;
import pl.mylittleworld.sit_them.conditions.Condition;

public class Control {

    private People onePeopleGeneration=new People(); //bullsheet
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
    public void userWantsToDeleteGuests(Person ... people){
        storageAssistant.deleteGuests(people);
    }
    public void userWantsToEditGuests(Person ... people){

    }

    public void userWantsToAddTables(Table... tables){

    }
    public void userWantsToDeleteTables(Table ... tables){

    }

    public void userWantsToAddCondition(Condition... conditions){

    }
    public void userWantsToDeleteCondition(Condition... conditions){

    }

    public void getConditionsListForDisplay(Storage.GetConditionsListener listener) {
        storageAssistant.getConditionsList(listener);
    }
}
