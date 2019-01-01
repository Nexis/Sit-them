package pl.mylittleworld.usadz_ich.genetics;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import pl.mylittleworld.database.temporary_storage.Seat;
import pl.mylittleworld.database.tables.ChairT;
import pl.mylittleworld.database.tables.PersonT;
import pl.mylittleworld.usadz_ich.SittingPlan;
import pl.mylittleworld.usadz_ich.conditions.Conditions;

public class GeneticAlgorithms {

    private Random random = new Random();

    private final int populationSize = 10;

    private ArrayList<ChairT> chairTS;
    private ArrayList<PersonT> people;
    private Conditions conditionTS;

    private int numberOfSits;

    public GeneticAlgorithms(ArrayList<ChairT> chairTS, ArrayList<PersonT> people, Conditions conditionTS) {
        this.chairTS = chairTS;
        this.people = people;
        this.conditionTS = conditionTS;
        numberOfSits = people.size();
    }

    /**
     * @return number from 0 (inclusive) to bound (exclusive)
     **/
    private int randId() {
        return random.nextInt(numberOfSits);
    }

    SittingPlan getNewEmptySittingPlan() {

        ArrayList<Seat> seats = new ArrayList<>();

        for (ChairT chairT : chairTS) {
            seats.add(new Seat(chairT, -1));
        }

        return new SittingPlan(seats, conditionTS, people);

    }

    List<SittingPlan> randFirstPopulation() {

        List<SittingPlan> sittingPlans = new ArrayList<SittingPlan>();
        for (int i = 0; i < populationSize; ++i) {
            sittingPlans.add(getNewEmptySittingPlan());
        }

        int randPersonIndex;
        int numberOfSits = sittingPlans.get(0).getNumberOfSits();

        for (SittingPlan sittingPlan : sittingPlans) {
            randPersonIndex = randId();
            for (int i = 0; i < numberOfSits; ++i) {
                while (sittingPlan.isPersonUnderThisIndexSitted(randPersonIndex)) {
                    randPersonIndex = randId();
                }
                sittingPlan.getSitAt(i).setPersonID(people.get(randPersonIndex).getPersonID());
            }
        }
        return sittingPlans;
    }

    /**
     * Function rotate randomed fragment
     * parameter can be broken by method
     *
     * @param sittingPlan to mutate
     * @return mutated sittingPlan
     */
    SittingPlan mutateBig(SittingPlan sittingPlan) {

        int startIndex = randId();
        int endIndex = randId();

        if (startIndex > endIndex) {
            int temp = startIndex;
            startIndex = endIndex;
            endIndex = temp;
        }
        List<Seat> copiedSittingPlan = getDeepCopyOfSeatList(sittingPlan.getSeatList());
        SittingPlan mutated = new SittingPlan(copiedSittingPlan, conditionTS, people);
        int backIndex = endIndex;

        for (int i = 0; i < sittingPlan.getNumberOfSits(); ++i) {
            if (i < startIndex || i > endIndex) {
                rewrite(i, i, sittingPlan, mutated);
            } else {
                rewrite(backIndex, i, sittingPlan, mutated);
                --backIndex;
            }
        }
        return mutated;
    }

    private void rewrite(int srcIndex, int targetIndex, SittingPlan src, SittingPlan target) {
        Seat tempSeat = src.getSitAt(srcIndex);
        target.getSitAt(targetIndex).setPersonID(tempSeat.getPersonID());
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

    SittingPlan mutate(SittingPlan sittingPlan) {
        if (sittingPlan.getNumberOfSits() > 1) {
            int sitNumber1 = randId();
            int sitNumber2 = randId();

            while (sitNumber1 == sitNumber2) {
                sitNumber2 = randId();
            }
            sittingPlan.swapPeopleAtSits(sitNumber1, sitNumber2);

        }
        return sittingPlan;
    }

    SittingPlan copulate(SittingPlan mother, SittingPlan father) throws SomethingWentTerriblyWrongException {
        if (mother.getNumberOfSits() != father.getNumberOfSits()) {
            throw new SomethingWentTerriblyWrongException();
        }

        int numberOfSits = mother.getNumberOfSits();

        int startOfMotherGenom = randId();
        int endOfMotherGenom = (startOfMotherGenom + numberOfSits / 2) % numberOfSits;

        if (startOfMotherGenom > endOfMotherGenom) {
            int temp = startOfMotherGenom;
            startOfMotherGenom = endOfMotherGenom;
            endOfMotherGenom = temp;
        }

        List<Seat> seatList = getDeepCopyOfSeatList(mother.getSeatList());
        SittingPlan descendant = new SittingPlan(seatList, conditionTS, people);

        //-1 for all personID
        for (Seat seat : descendant.getSeatList()) {
            seat.setPersonID(-1);
        }

        for (int index = startOfMotherGenom; index < endOfMotherGenom; ++index) {
            descendant.getSitAt(index).setPersonID(mother.getSitAt(index).getPersonID());
        }

        for (int i = 0, index = 0; i < numberOfSits; ++i) {
            if (i == startOfMotherGenom) {
                i = endOfMotherGenom;
            }
            while (descendant.isThisPersonSitted(father.getSitAt(index).getPersonID())) {
                ++index;
            }
            descendant.getSitAt(i).setPersonID(father.getSitAt(index).getPersonID());
            ++index;
        }


        return descendant;
    }

    //todo change method to fortune circle with parent/child difference
    List<SittingPlan> naturalSelection(List<SittingPlan> sittingPlans, int numberToRemove) {

        Collections.sort(sittingPlans, new Comparator<SittingPlan>() {
            @Override
            public int compare(SittingPlan o1, SittingPlan o2) {
                if (o1.getAdaptationLvl() < o2.getAdaptationLvl()) {
                    return -1;
                } else if (o1.getAdaptationLvl() == o2.getAdaptationLvl()) {
                    return 0;
                } else {
                    return 1;
                }

            }
        });

        for (int i = 0; i < numberToRemove; ++i) {
            sittingPlans.remove(0);
        }

        return sittingPlans;
    }

    @Nullable
    private SittingPlan isTherePerfectOne(List<SittingPlan> sittingPlans) {
        for (SittingPlan sittingPlan : sittingPlans) {
            // System.out.println(sittingPlan.toString());
            if (sittingPlan.getAdaptationLvl() == 100) {
                return sittingPlan;
            }
        }
        return null;
    }

    public SittingPlan evolution() throws SomethingWentTerriblyWrongException {

        List<SittingPlan> population = randFirstPopulation();
        SittingPlan perfect = null;

        int rand;
//todo dobrac eksperymentalnie prawdopodobienstwa
        while (perfect == null) {
            int amountOfNewPopulationMembers = 0;

            rand = random.nextInt(100);

            //MUTATE
            if (rand < 70) {
                int which = randId();
                SittingPlan mutated = mutate(population.get(which));
                population.set(which, mutated);
            }
            //MUTATE BIG
            else if (rand < 85) {
                int which = randId();
                SittingPlan mutated = mutateBig(population.get(which));
                population.set(which, mutated);
            } else {
                ++amountOfNewPopulationMembers;
                int which1 = randId();
                int which2 = randId();
                SittingPlan newMember = copulate(population.get(which1), population.get(which2));
                population.add(newMember);
            }

            population = naturalSelection(population, amountOfNewPopulationMembers);

            perfect = isTherePerfectOne(population);
        }

        return perfect;
    }
}
