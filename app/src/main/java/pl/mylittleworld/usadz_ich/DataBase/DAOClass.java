package pl.mylittleworld.usadz_ich.DataBase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

@Dao
public interface DAOClass {

    //Tables///////////////////////////////////////////////////////////////////////////

    @Insert
    public int insertTable(TableT ... tables);

    @Delete
    public int deleteTable(TableT ...tables);

    //People//////////////////////////////////////////////////////////////////////////

    @Insert
    public int insertPerson(PersonT ...people);

    @Update
    public int updatePerson(PersonT ...people);

    @Delete
    public int deletePerson(PersonT ...people);

}
