package pl.mylittleworld.database.temporary_storage;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import pl.mylittleworld.database.NameId;
import pl.mylittleworld.database.tables.PersonT;

//todo not thread safe
public class People  {

    private static  ArrayList<PersonT> temporarySToragePeople=null;
    private static boolean initialized=false;

    public static ArrayList<PersonT> getTemporaryStoragePeople() {
        return temporarySToragePeople;
    }
    public static ArrayList<NameId> getTemporaryStoragePeopleAsNameId() {
        ArrayList<NameId> nameIdArrayList= new ArrayList<>();
        for(PersonT personT :temporarySToragePeople){
            NameId nameId= new NameId(personT.getName(),personT.getPersonID());
            nameIdArrayList.add(nameId);
        }
        return nameIdArrayList;
    }
    public static String getNameOfPerson(int id){
        for(PersonT personT:temporarySToragePeople){
            if(personT.getPersonID()==id){
                return personT.getName();
            }
        }
        throw new NoSuchElementException("no such person with that id");
    }

    public static boolean isInitialized(){
        return initialized;
    }

    public static void initialize(ArrayList<PersonT> personTS){
        if(temporarySToragePeople==null) {
            temporarySToragePeople = personTS;
            initialized = true;
        }
    }
    public static void update(@NonNull ArrayList<PersonT> personTS){
        if(temporarySToragePeople!=null) {
            temporarySToragePeople = personTS;
        }
    }
}
