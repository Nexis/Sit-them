package pl.mylittleworld.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.Cursor;

import java.util.List;

import pl.mylittleworld.database.tables.ConditionT;
import pl.mylittleworld.database.tables.PersonT;
import pl.mylittleworld.database.tables.TableT;


@Dao
public interface DAOClass {

    //Tables///////////////////////////////////////////////////////////////////////////

    @Insert
    public void insertTable(TableT... tables);

    @Delete
    public int deleteTable(TableT ...tables);

    //People//////////////////////////////////////////////////////////////////////////

    @Insert
    public void insertPerson(PersonT...people);

    @Update
    public int updatePerson(PersonT ...people);

    @Delete
    public int deletePerson(PersonT ...people);

    @Query("SELECT *  FROM PersonT")
    List<PersonT> getGuests();

    @Query("SELECT * FROM ConditionT")
    List<ConditionT> getConditionsList();

    @Insert
    void addConditions(ConditionT... conditionTS);
}
