package pl.mylittleworld.database.temporary_storage;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import pl.mylittleworld.usadz_ich.SittingPlan;

public class TemporaryStorageSittingPlan {
    private static SittingPlan sittingPlan;
    private static boolean isActual;
    private static boolean exists;

    @Nullable
    public static SittingPlan getActualSittingPlan(){
        if(!exists) {
            return null;
        }
        return sittingPlan;
    }
    public static void insertActualSittingPlan(@NonNull SittingPlan sittingPlan){
        TemporaryStorageSittingPlan.sittingPlan=sittingPlan;
        exists=true;
        setActual(true);
    }
    public static boolean isActual(){
        return isActual;
    }
    public static boolean exists(){
        return exists;
    }
    public static void setActual(boolean state){
        isActual=state;
    }
}
