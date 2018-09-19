package pl.mylittleworld.usadz_ich;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.junit.Assert;

import pl.mylittleworld.sit_them.Chair;
import pl.mylittleworld.sit_them.Seat;

import static org.mockito.Mockito.when;

public class SeatTest {

    Chair mockChair1= Mockito.mock(Chair.class);

    Seat seat= new Seat(mockChair1,6);

    @Before
    public void initialize() {
        when(mockChair1.getX()).thenReturn(1);
        when(mockChair1.getY()).thenReturn(5);
        when(mockChair1.getTableID()).thenReturn(3);
    }
    @Test
    public void areThoseSitsCloseToEachOther(){

        //next to

        Chair nextToChair= Mockito.mock(Chair.class);
        when(nextToChair.getX()).thenReturn(2);
        when(nextToChair.getY()).thenReturn(5);
        when(nextToChair.getTableID()).thenReturn(3);

        Seat seat1=new Seat(nextToChair,90);


        Assert.assertTrue("Problem with sitting close to each other",seat.areThoseSitsCloseToEachOther(seat1,false,false));

        Chair notNextToChair= Mockito.mock(Chair.class);
        when(nextToChair.getX()).thenReturn(3);
        when(nextToChair.getY()).thenReturn(5);
        when(nextToChair.getTableID()).thenReturn(3);

        Seat seat0=new Seat(nextToChair,90);


        Assert.assertFalse("Problem with sitting close to each other",seat.areThoseSitsCloseToEachOther(seat0,false,false));



        //diagonally
        Chair diagonalChair= Mockito.mock(Chair.class);
        when(diagonalChair.getX()).thenReturn(2);
        when(diagonalChair.getY()).thenReturn(6);
        when(diagonalChair.getTableID()).thenReturn(3);

        Seat seat3=new Seat(diagonalChair,90);

        Assert.assertTrue("Problem with sitting close to each other",seat.areThoseSitsCloseToEachOther(seat3,false,true));
        Assert.assertFalse("Problem with sitting close to each other",seat.areThoseSitsCloseToEachOther(seat3,false,false));


        Chair notDiagonalChair= Mockito.mock(Chair.class);
        when(notDiagonalChair.getX()).thenReturn(3);
        when(notDiagonalChair.getY()).thenReturn(6);
        when(notDiagonalChair.getTableID()).thenReturn(3);

        Seat seat4=new Seat(notDiagonalChair,90);

        Assert.assertFalse("Problem with sitting close to each other",seat.areThoseSitsCloseToEachOther(seat4,false,true));
        Assert.assertFalse("Problem with sitting close to each other",seat.areThoseSitsCloseToEachOther(seat4,false,false));


    }
}
