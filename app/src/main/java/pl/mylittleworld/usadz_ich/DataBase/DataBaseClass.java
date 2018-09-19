package pl.mylittleworld.usadz_ich.DataBase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {TableT.class,PersonT.class,ChairT.class},version=1)
public abstract class DataBaseClass extends RoomDatabase{

    public abstract DAOClass getDAOClass();
}
