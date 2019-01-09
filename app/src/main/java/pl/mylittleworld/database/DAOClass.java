package pl.mylittleworld.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import pl.mylittleworld.database.tables.ConditionT;
import pl.mylittleworld.database.tables.PersonT;
import pl.mylittleworld.database.tables.TableT;
import pl.mylittleworld.database.tables.TablesPlanT;

/**
 * This class is a room Dao-- Data access object
 */

@Dao
public interface DAOClass {

    //Tables///////////////////////////////////////////////////////////////////////////

    @Insert
    void insertTable(TableT... tables);

    @Delete
    int deleteTable(TableT... tables);

    @Insert
    void addTablesPlanT(TablesPlanT tablesPlanT);

    @Delete
    void deleteTablesPlanT(TablesPlanT tablesPlanT);

    //People//////////////////////////////////////////////////////////////////////////

    @Insert
    void insertPerson(PersonT... people);

    @Update
    int updatePerson(PersonT... people);

    @Delete
    int deletePerson(PersonT... people);

    @Query("SELECT *  FROM PersonT")
    List<PersonT> getGuests();

    @Query("SELECT * FROM ConditionT")
    List<ConditionT> getConditionsList();

    @Query("SELECT * FROM TableT")
    List<TableT> getTablesList();

    @Insert
    void addConditions(ConditionT... conditionTS);

    @Query("DELETE FROM TableT WHERE tableID=:id")
    void deleteTableWithId(final int id);

    @Query ("DELETE FROM TableT")
    void dropTables();

    @Query ("DELETE FROM ConditionT")
    void dropConditions();

    @Query ("DELETE FROM PersonT")
    void dropPeople();

    @Query("DELETE FROM ConditionT WHERE conditionID=:id")
    void deleteConditionWithId(int id);
}
