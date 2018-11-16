package pl.mylittleworld.database;

import android.arch.persistence.room.TypeConverter;

import pl.mylittleworld.usadz_ich.TABLE_TYPE;
import pl.mylittleworld.usadz_ich.conditions.CONDITIONS_OPTIONS;

public class TypesConverter {

    @TypeConverter
    public static CONDITIONS_OPTIONS stringToCondition(String name) {
        return CONDITIONS_OPTIONS.valueOf(name);
    }

    @TypeConverter
    public static String conditionToString(CONDITIONS_OPTIONS condition) {
        return condition.toString();
    }

    @TypeConverter
    public static TABLE_TYPE stringToTableType(String name) {
        return TABLE_TYPE.valueOf(name);
    }

    @TypeConverter
    public static String tableTypeToString(TABLE_TYPE type) {
        return type.toString();
    }
}
