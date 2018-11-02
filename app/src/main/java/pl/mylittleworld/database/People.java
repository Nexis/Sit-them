package pl.mylittleworld.database;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import pl.mylittleworld.database.tables.PersonT;

public class People {

    private static  ArrayList<PersonT> temporarySToragePeople=null;
    private static boolean initialized=false;

    public static ArrayList<PersonT> getTemporaryStoragePeople() {
        return temporarySToragePeople;
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