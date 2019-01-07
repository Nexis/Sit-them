package pl.mylittleworld.usadz_ich;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import pl.mylittleworld.database.tables.ChairT;
import pl.mylittleworld.database.tables.TableT;
import pl.mylittleworld.database.temporary_storage.Seat;
import pl.mylittleworld.database.temporary_storage.Tables;

import static org.mockito.Mockito.when;

public class SeatTest {

    ChairT mockChair1= Mockito.mock(ChairT.class);

    Seat seat= new Seat(mockChair1,6);
    ArrayList<TableT> tableTS= new ArrayList<>();

    TableT table1= Mockito.mock(TableT.class);


    @Before
    public void initialize() {
        when(mockChair1.getX()).thenReturn(1);
        when(mockChair1.getY()).thenReturn(5);
        when(mockChair1.getTableID()).thenReturn(3);

        Mockito.when(table1.getTableID()).thenReturn(3);
        Mockito.when(table1.getTableType()).thenReturn(TABLE_TYPE.RECTANGULAR);
        tableTS.add(table1);

        Tables.initialize(tableTS);
        Tables.update(tableTS);
    }
    @Test
    public void areThoseSitsCloseToEachOther(){

        //next to

        ChairT nextToChair= Mockito.mock(ChairT.class);
        when(nextToChair.getX()).thenReturn(2);
        when(nextToChair.getY()).thenReturn(5);
        when(nextToChair.getTableID()).thenReturn(3);

        Seat seat1=new Seat(nextToChair,90);


        Assert.assertTrue("Problem with sitting close to each other",seat.areThoseSitsCloseToEachOther(seat1,false,false));

        ChairT notNextToChair= Mockito.mock(ChairT.class);
        when(nextToChair.getX()).thenReturn(3);
        when(nextToChair.getY()).thenReturn(5);
        when(nextToChair.getTableID()).thenReturn(3);

        Seat seat0=new Seat(nextToChair,90);


        Assert.assertFalse("Problem with sitting close to each other",seat.areThoseSitsCloseToEachOther(seat0,false,false));



        //diagonally
        ChairT diagonalChair= Mockito.mock(ChairT.class);
        when(diagonalChair.getX()).thenReturn(2);
        when(diagonalChair.getY()).thenReturn(6);
        when(diagonalChair.getTableID()).thenReturn(3);

        Seat seat3=new Seat(diagonalChair,90);

        Assert.assertTrue("Problem with sitting close to each other",seat.areThoseSitsCloseToEachOther(seat3,false,true));
        Assert.assertFalse("Problem with sitting close to each other",seat.areThoseSitsCloseToEachOther(seat3,false,false));


        ChairT notDiagonalChair= Mockito.mock(ChairT.class);
        when(notDiagonalChair.getX()).thenReturn(3);
        when(notDiagonalChair.getY()).thenReturn(6);
        when(notDiagonalChair.getTableID()).thenReturn(3);

        Seat seat4=new Seat(notDiagonalChair,90);

        Assert.assertFalse("Problem with sitting close to each other",seat.areThoseSitsCloseToEachOther(seat4,false,true));
        Assert.assertFalse("Problem with sitting close to each other",seat.areThoseSitsCloseToEachOther(seat4,false,false));


    }
}
