package pl.mylittleworld.usadz_ich.genetics;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import pl.mylittleworld.database.People;
import pl.mylittleworld.database.tables.ChairT;
import pl.mylittleworld.database.tables.PersonT;
import pl.mylittleworld.usadz_ich.conditions.Conditions;
import pl.mylittleworld.database.Seat;
import pl.mylittleworld.usadz_ich.SittingPlan;

import static junit.framework.Assert.fail;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class GeneticAlgorithmsTest {

    private GeneticAlgorithms geneticAlgorithms= new GeneticAlgorithms(null,null,null);

    @Test()
    public void mutateBigTest() {

        List<Seat> seatList = new ArrayList<>();
        ChairT chair = Mockito.mock(ChairT.class);

        for (int i = 0; i < 30; ++i) {
            Seat seat = new Seat(chair, i);
            seatList.add(seat);
        }

        List<PersonT> people = new ArrayList<>();
        PersonT personT = Mockito.mock(PersonT.class);

        for (int i = 0; i < 30; ++i) {
            people.add(personT);
        }

        SittingPlan sittingPlan = geneticAlgorithms.mutateBig(new SittingPlan(seatList, Mockito.mock(Conditions.class), people));

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
        ChairT chair = Mockito.mock(ChairT.class);

        for (int i = 0; i < 30; ++i) {
            Seat seat = new Seat(chair, i);
            seatList.add(seat);
        }

        List<PersonT> people = new ArrayList<>();
        PersonT personT = Mockito.mock(PersonT.class);

        for (int i = 0; i < 30; ++i) {
            people.add(personT);
        }

        SittingPlan sittingPlan = geneticAlgorithms.mutate(new SittingPlan(seatList, Mockito.mock(Conditions.class), people));

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
        ChairT chair = Mockito.mock(ChairT.class);

        for (int i = 0; i < 30; ++i) {
            Seat seat = new Seat(chair, i);
            seatList.add(seat);
        }
        List<Seat> seatList2 = new ArrayList<>();
        ChairT chair2 = Mockito.mock(ChairT.class);

        for (int i = 0; i < 30; ++i) {
            Seat seat = new Seat(chair, 29-i);
            seatList2.add(seat);
        }
        List<PersonT> people = new ArrayList<>();
        PersonT personT = Mockito.mock(PersonT.class);

        for (int i = 0; i < 30; ++i) {
            people.add(personT);
        }

        SittingPlan sittingPlan1 = new SittingPlan(seatList, Mockito.mock(Conditions.class), people);
        SittingPlan sittingPlan2= new SittingPlan(seatList2, Mockito.mock(Conditions.class), people);

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

        sittingPlans=geneticAlgorithms.naturalSelection(sittingPlans,15);

        for(int i=14;i>=0;--i){
            //checking if stays only the highest ones 29 28 27 ... 15
            if(sittingPlans.get(14-i).getAdaptationLvl()!=(29-i)){
                fail();
            }
        }

    }
}
