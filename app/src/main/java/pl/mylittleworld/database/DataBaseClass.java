package pl.mylittleworld.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import pl.mylittleworld.database.tables.ChairT;
import pl.mylittleworld.database.tables.ConditionT;
import pl.mylittleworld.database.tables.PersonT;
import pl.mylittleworld.database.tables.TableT;
import pl.mylittleworld.usadz_ich.conditions.ConditionTypesConverter;

@Database(entities = {TableT.class,PersonT.class,ChairT.class,ConditionT.class},version=2)
@TypeConverters({ConditionTypesConverter.class})
public abstract class DataBaseClass extends RoomDatabase{

    public abstract DAOClass getDao();
}
