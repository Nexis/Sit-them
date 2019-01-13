package pl.mylittleworld.usadz_ich.genetics;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import pl.mylittleworld.database.tables.ChairT;
import pl.mylittleworld.database.tables.PersonT;
import pl.mylittleworld.database.temporary_storage.Seat;
import pl.mylittleworld.usadz_ich.SittingPlan;
import pl.mylittleworld.usadz_ich.conditions.Conditions;

public class RandomAlgorithm {

    private Random random = new Random();

    private ArrayList<ChairT> chairTS;
    private ArrayList<PersonT> people;
    private Conditions conditionTS;

    private int numberOfSits;

    public RandomAlgorithm(ArrayList<ChairT> chairTS, ArrayList<PersonT> people, Conditions conditionTS) {
        this.chairTS = chairTS;
        this.people = people;
        this.conditionTS = conditionTS;
        numberOfSits = people.size();
    }

    private void shuffleSittingPlan(SittingPlan sittingPlan){
        List <Integer> idList=new ArrayList<>();

        for (int i = 0; i < numberOfSits; ++i) {
            idList.add(people.get(i).getPersonID());
        }

        Collections.shuffle(idList);
        for (int i = 0; i < numberOfSits; ++i) {
            sittingPlan.getSitAt(i).setPersonID(idList.get(i));
        }
    }
    private static List<Seat> getDeepCopyOfSeatList(final @NonNull List<Seat> seatList) {
        final List<Seat> copiedList = new ArrayList<>();

        for (Seat tempSeat : seatList) {
            ChairT chairT = tempSeat.getChairT();
            int personId = tempSeat.getPersonID();

            copiedList.add(new Seat(chairT, personId));
        }
        return copiedList;
    }

    private SittingPlan getDeepCopy(SittingPlan sittingPlan){
        List<Seat> copiedSittingPlan = getDeepCopyOfSeatList(sittingPlan.getSeatList());
        SittingPlan copy = new SittingPlan(copiedSittingPlan, conditionTS, people);
        return copy;
    }

    /**
     * This method generates solution randomly
     */
    public SittingPlan generateSolution(){
        SittingPlan sittingPlan =new GeneticAlgorithms(chairTS,people,conditionTS).getNewEmptySittingPlan();
        shuffleSittingPlan(sittingPlan);
        int adaptationLvl=sittingPlan.getAdaptationLvl();
        SittingPlan best= getDeepCopy(sittingPlan);

        long startTime = System.currentTimeMillis();
        long duration= System.currentTimeMillis()-startTime;

        while (duration< 6000) {
            if(sittingPlan.getAdaptationLvl()> adaptationLvl){
                best= getDeepCopy(sittingPlan);
            }
            duration=System.currentTimeMillis()-startTime;
        }
        return best;
    }
}
