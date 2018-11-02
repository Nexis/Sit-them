package pl.mylittleworld.usadz_ich.logic;


import pl.mylittleworld.database.Storage;
import pl.mylittleworld.database.tables.PersonT;
import pl.mylittleworld.database.tables.TableT;
import pl.mylittleworld.usadz_ich.conditions.Condition;

public class Control {

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

    public void userWantsToAddTables(TableT... tables){

    }
    public void userWantsToDeleteTables(TableT ... tables){

    }

    public void userWantsToAddCondition(Condition... conditions){

    }
    public void userWantsToDeleteCondition(Condition... conditions){

    }

    public void getConditionsListForDisplay(Storage.GetConditionsListener listener) {
        storageAssistant.getConditionsList(listener);
    }
}
