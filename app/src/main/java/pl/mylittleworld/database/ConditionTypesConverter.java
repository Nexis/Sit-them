package pl.mylittleworld.database;

import android.arch.persistence.room.TypeConverter;

import pl.mylittleworld.sit_them.conditions.CONDITIONS_OPTIONS;

public class ConditionTypesConverter {

    @TypeConverter
    public static CONDITIONS_OPTIONS stringToCondition(String name) {
        return CONDITIONS_OPTIONS.valueOf(name);
    }

    @TypeConverter
    public static String conditionToString(CONDITIONS_OPTIONS condition) {
        return condition.toString();
    }
}
