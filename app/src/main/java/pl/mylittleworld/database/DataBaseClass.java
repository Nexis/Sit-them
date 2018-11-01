package pl.mylittleworld.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

@Database(entities = {TableT.class,PersonT.class,ChairT.class,ConditionT.class},version=1)
@TypeConverters({ConditionTypesConverter.class})
public abstract class DataBaseClass extends RoomDatabase{

    public abstract DAOClass getDao();
}
