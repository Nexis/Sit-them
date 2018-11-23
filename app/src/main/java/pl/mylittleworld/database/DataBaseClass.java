package pl.mylittleworld.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import pl.mylittleworld.database.tables.ChairT;
import pl.mylittleworld.database.tables.ConditionT;
import pl.mylittleworld.database.tables.GroupT;
import pl.mylittleworld.database.tables.PersonT;
import pl.mylittleworld.database.tables.TableT;
import pl.mylittleworld.database.tables.TablesPlanT;

@Database(entities = {TableT.class,PersonT.class,ChairT.class,ConditionT.class,TablesPlanT.class,GroupT.class},version=7)
@TypeConverters({TypesConverter.class})
public abstract class DataBaseClass extends RoomDatabase{

    public abstract DAOClass getDao();
}
