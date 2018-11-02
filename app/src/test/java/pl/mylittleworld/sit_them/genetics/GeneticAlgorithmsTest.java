package pl.mylittleworld.sit_them.genetics;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import pl.mylittleworld.sit_them.model.Chair;
import pl.mylittleworld.usadz_ich.conditions.Conditions;
import pl.mylittleworld.sit_them.model.People;
import pl.mylittleworld.database.Seat;
import pl.mylittleworld.usadz_ich.SittingPlan;
import pl.mylittleworld.usadz_ich.genetics.GeneticAlgorithms;
import pl.mylittleworld.usadz_ich.genetics.SomethingWentTerriblyWrongException;

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

    @Test
    public void copulateTest(){
        List<Seat> seatList = new ArrayList<>();
        Chair chair = Mockito.mock(Chair.class);

        for (int i = 0; i < 30; ++i) {
            Seat seat = new Seat(chair, i);
            seatList.add(seat);
        }
        List<Seat> seatList2 = new ArrayList<>();
        Chair chair2 = Mockito.mock(Chair.class);

        for (int i = 0; i < 30; ++i) {
            Seat seat = new Seat(chair, 29-i);
            seatList2.add(seat);
        }

        SittingPlan sittingPlan1 = new SittingPlan(seatList, Mockito.mock(Conditions.class), Mockito.mock(People.class));
        SittingPlan sittingPlan2= new SittingPlan(seatList2,Mockito.mock(Conditions.class),Mockito.mock(People.class));

        try {
            SittingPlan sittingPlan3 = geneticAlgorithms.copulate(sittingPlan1, sittingPlan2);

            //check if all people are sitted
            for(int i=0;i<30;++i){
                if(!sittingPlan3.isThisPersonSitted(i)){
                    fail();
                }
            }
        }
        catch (SomethingWentTerriblyWrongException ex){
            fail();
        }


    }

    @Test
    public void naturalSelectionTest(){

        List <SittingPlan> sittingPlans= new ArrayList<>(30);
        for(int i=0;i<30;++i){
            SittingPlan temp=Mockito.mock(SittingPlan.class);
            Mockito.when(temp.getAdaptationLvl()).thenReturn(29-i); //fullfilling list with adaptations lvl as 29 28 27... 0
            sittingPlans.add(temp);
        }

        sittingPlans=geneticAlgorithms.naturalSelection(sittingPlans);

        for(int i=14;i>=0;--i){
            //checking if stays only the highest ones 29 28 27 ... 15
            if(sittingPlans.get(14-i).getAdaptationLvl()!=(29-i)){
                fail();
            }
        }

    }
}
