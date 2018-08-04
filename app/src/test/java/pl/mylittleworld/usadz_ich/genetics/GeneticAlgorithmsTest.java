package pl.mylittleworld.usadz_ich.genetics;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import pl.mylittleworld.usadz_ich.Chair;
import pl.mylittleworld.usadz_ich.Conditions;
import pl.mylittleworld.usadz_ich.People;
import pl.mylittleworld.usadz_ich.Seat;
import pl.mylittleworld.usadz_ich.SittingPlan;

import static junit.framework.Assert.fail;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class GeneticAlgorithmsTest {

    private GeneticAlgorithms geneticAlgorithms = new GeneticAlgorithms();

    @Test()
    public void mutateBigTest() {

        List<Seat> seatList = new ArrayList<>();
        Chair chair = Mockito.mock(Chair.class);

        for (int i = 0; i < 30; ++i) {
            Seat seat = new Seat(chair, i);
            seatList.add(seat);
        }

        SittingPlan sittingPlan = geneticAlgorithms.mutateBig(new SittingPlan(seatList, Mockito.mock(Conditions.class), Mockito.mock(People.class)));

        int check = -1;

        for (int i = 0; i < sittingPlan.getNumberOfSits(); ++i) {
            if (sittingPlan.getSitAt(i).getPersonID() != seatList.get(i).getPersonID()) {
                if (check == -1) {
                    check = sittingPlan.getSitAt(i).getPersonID();
                } else {
                    if (check != sittingPlan.getSitAt(i).getPersonID() - 1) {
                        fail();
                    } else {
                        --check;
                    }
                }
            }
        }


    }

    @Test
    public void mutateTest() {
        List<Seat> seatList = new ArrayList<>();
        Chair chair = Mockito.mock(Chair.class);

        for (int i = 0; i < 30; ++i) {
            Seat seat = new Seat(chair, i);
            seatList.add(seat);
        }

        SittingPlan sittingPlan = geneticAlgorithms.mutate(new SittingPlan(seatList, Mockito.mock(Conditions.class), Mockito.mock(People.class)));

        int firstSwapedIndex = -1;
        int secondSwapedIndex = -1;
        for (int i = 0; i < sittingPlan.getNumberOfSits(); ++i) {
            if (sittingPlan.getSitAt(i).getPersonID() != i) {
                if (firstSwapedIndex == -1) {
                    firstSwapedIndex = sittingPlan.getSitAt(i).getPersonID();
                } else if (secondSwapedIndex == -1) {
                    secondSwapedIndex = sittingPlan.getSitAt(i).getPersonID();
                } else {
                    fail();
                }
            }
        }
        if (firstSwapedIndex == -1 || secondSwapedIndex == -1) {
            fail();
        }
        Assert.assertTrue(seatList.get(firstSwapedIndex).getPersonID() == secondSwapedIndex);
        Assert.assertTrue(seatList.get(secondSwapedIndex).getPersonID() == firstSwapedIndex);


    }
}
