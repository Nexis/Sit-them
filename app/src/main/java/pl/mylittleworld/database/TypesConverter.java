package pl.mylittleworld.database;

import android.arch.persistence.room.TypeConverter;

import pl.mylittleworld.usadz_ich.TABLE_TYPE;
import pl.mylittleworld.usadz_ich.conditions.CONDITIONS_OPTIONS;

/**
 * This class provides type conversion from and to enums: CONDITIONS_OPTIONS and TABLE_TYPE which is neccessary
 * for storing this types object into database
 */
public class TypesConverter {

    /**
     * @param name is a String which corresponds to one of possible CONDITION_OPTIONS values
     * @return value of CONDITION_OPTIONS which corresponds to given name
     */
    @TypeConverter
    public static CONDITIONS_OPTIONS stringToCondition(String name) {
        return CONDITIONS_OPTIONS.valueOf(name);
    }

    /**
     * @param condition is a CONDITION_OPTIONS
     * @return value of CONDITION_OPTIONS as a corresponded String
     */
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
