package pl.mylittleworld.database.temporary_storage;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import pl.mylittleworld.database.NameId;
import pl.mylittleworld.database.tables.PersonT;

/**
 * This class is a tamporary storage for people list
 */
public class People  {

    private static  ArrayList<PersonT> temporarySToragePeople=null;
    private static boolean initialized=false;

    public static ArrayList<PersonT> getTemporaryStoragePeople() {
        return temporarySToragePeople;
    }

    /**
     * Gives  people list from temporary storaged one
     * @return ArrayList of type name id which is list of people
     */
    public static ArrayList<NameId> getTemporaryStoragePeopleAsNameId() {
        ArrayList<NameId> nameIdArrayList= new ArrayList<>();
        for(PersonT personT :temporarySToragePeople){
            NameId nameId= new NameId(personT.getName(),personT.getPersonID());
            nameIdArrayList.add(nameId);
        }
        return nameIdArrayList;
    }

    /**
     *
     * @param id is id of person
     * @return name of the person whose id was given
     */
    public static String getNameOfPerson(int id){
        for(PersonT personT:temporarySToragePeople){
            if(personT.getPersonID()==id){
                return personT.getName();
            }
        }
        throw new NoSuchElementException("no such person with that id");
    }

    /**
     *
     * @return if temporary storage has been initialized
     */
    public static boolean isInitialized(){
        return initialized;
    }

    /**
     * Provides posibility to initialize people temporary storage
     * @param personTS list of people, values from this list will be used for initializing people temporary storage
     */
    public static void initialize(ArrayList<PersonT> personTS){
        if(temporarySToragePeople==null) {
            temporarySToragePeople = personTS;
            initialized = true;
        }
    }

    /**
     * Provides posibility to update people temporary storage
     * @param personTS list of people, values from this list will be used for updating people temporary storage
     *                 actual list is replaced with given one
     */
    public static void update(@NonNull ArrayList<PersonT> personTS){
        if(temporarySToragePeople!=null) {
            temporarySToragePeople = personTS;
        }
    }
}
