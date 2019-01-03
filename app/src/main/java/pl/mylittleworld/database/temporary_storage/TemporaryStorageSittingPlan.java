package pl.mylittleworld.database.temporary_storage;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import pl.mylittleworld.usadz_ich.SittingPlan;

/**
 * This class provides temporary storage for sitting plan- one lastly generated
 */
public class TemporaryStorageSittingPlan {
    private static SittingPlan sittingPlan;
    private static boolean isActual;
    private static boolean exists;

    /**
     *
     * @return returns lastly generated plan or null when there was none generated
     */
    @Nullable
    public static SittingPlan getActualSittingPlan(){
        if(!exists) {
            return null;
        }
        return sittingPlan;
    }

    /**
     *
     * @param sittingPlan is saved as actual one
     */
    public static void insertActualSittingPlan(@NonNull SittingPlan sittingPlan){
        TemporaryStorageSittingPlan.sittingPlan=sittingPlan;
        exists=true;
        setActual(true);
    }

    /**
     * @return if plan is actual it means that it was no change in people, tables and conditions lists
     * since stored here SittingPlan was generated
     */
    public static boolean isActual(){
        return isActual;
    }

    /**
     *
     * @return if SittingPlan was ever generated
     */
    public static boolean exists(){
        return exists;
    }

    /**
     * @param state sets state of SittingPlan-- if it is actual which means that
     *  there was no change in people,tables and conditions list since it was generated
     */
    public static void setActual(boolean state){
        isActual=state;
    }
}
