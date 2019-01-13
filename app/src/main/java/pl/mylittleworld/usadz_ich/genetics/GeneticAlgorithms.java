package pl.mylittleworld.usadz_ich.genetics;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import pl.mylittleworld.database.tables.ChairT;
import pl.mylittleworld.database.tables.PersonT;
import pl.mylittleworld.database.temporary_storage.Seat;
import pl.mylittleworld.usadz_ich.SittingPlan;
import pl.mylittleworld.usadz_ich.conditions.Conditions;

/**
 * This class implements genetic algorithms
 */
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

    /**
     * creates new empty sitting plan with all object but with default values
     * @return this sittingPlan
     */
    SittingPlan getNewEmptySittingPlan() {

        ArrayList<Seat> seats = new ArrayList<>();

        for (ChairT chairT : chairTS) {
            seats.add(new Seat(chairT, -1));
        }

        return new SittingPlan(seats, conditionTS, people);

    }

    /**
     * generates N random sitting plans- first population for genetics algoritms
     * @return list of generated sitting plans
     */
    List<SittingPlan> randFirstPopulation() {

        List<SittingPlan> sittingPlans = new ArrayList<SittingPlan>();
        for (int i = 0; i < populationSize; ++i) {
            sittingPlans.add(getNewEmptySittingPlan());
        }

        List <Integer> idList=new ArrayList<>();

            for (int i = 0; i < numberOfSits; ++i) {
               idList.add(people.get(i).getPersonID());
            }
            for(SittingPlan sittingPlan: sittingPlans) {
                Collections.shuffle(idList);
                for (int i = 0; i < numberOfSits; ++i) {
                    sittingPlan.getSitAt(i).setPersonID(idList.get(i));
                }
            }
        return sittingPlans;
    }

    /**
     * Function rotate random fragment
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

    /**
     * This method rewrites values from seat at srcIndex in src to seat at targetIndex at target
     * @param srcIndex
     * @param targetIndex
     * @param src source sitting plan
     * @param target target sitting plan
     *
     */
    private void rewrite(int srcIndex, int targetIndex, SittingPlan src, SittingPlan target) {
        Seat tempSeat = src.getSitAt(srcIndex);
        target.getSitAt(targetIndex).setPersonID(tempSeat.getPersonID());
    }

    /**
     * This method makes and returns deep copy of given seatList
     * @param seatList seat List to copy
     * @return deep copy of given list
     */
    private static List<Seat> getDeepCopyOfSeatList(final @NonNull List<Seat> seatList) {
        final List<Seat> copiedList = new ArrayList<>();

        for (Seat tempSeat : seatList) {
            ChairT chairT = tempSeat.getChairT();
            int personId = tempSeat.getPersonID();

            copiedList.add(new Seat(chairT, personId));
        }
        return copiedList;
    }

    /**
     * This method makes mutation of genome, swap people at two seats -- in genetics algorithms naming convention
     * @param sittingPlan -- genom
     * @return mutated sitting plan
     */
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

    /**
     *This function make from two given sitting plans one new-- it's crossover in in genetics algorithms naming
     * @param mother sitting plan
     * @param father sitting plan
     * @return new sitting plan -- child in genetics alghorithms naming
     * @throws SomethingWentTerriblyWrongException it is thrown for example when in father's plan
     * is not the same amount of places
     */
    SittingPlan copulate(SittingPlan mother, SittingPlan father) throws SomethingWentTerriblyWrongException {

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

    /**
     * This method removes as many sitting plans as many new was created since last call, chance
     * that particular sitting plan would be removed is proportional to attitude of its target function
     * @param sittingPlans list of sitting plans
     * @param numberToRemove how many new sitting plans were created
     * @return list of sitting plans after removal some of them
     */

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
            boolean removed=false;
            while(removed==false) {
                int rand=random.nextInt(100);
                int index=0;
                if(rand > sittingPlans.get(index).getAdaptationLvl()) {
                    sittingPlans.remove(index);
                    removed= true;
                }
                ++index;
            }
        }

        return sittingPlans;
    }

    /**
     * Check if in a given list of sitting plans is one which suits perfect-- its target function returns 100%
     * @param sittingPlans list of sitting plans
     * @return perfect sitting plan if found otherwise null
     */
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

    /**
     * This function is randomly invoking other genetics methods
     * @return as good as can produce in given time or perfect sitting plan
     * @throws SomethingWentTerriblyWrongException
     */

    public SittingPlan evolution() throws SomethingWentTerriblyWrongException {

        List<SittingPlan> population = randFirstPopulation();
        SittingPlan perfect = null;
        SittingPlan currentBest=null;

        int rand;
        long startTime = System.currentTimeMillis();
        long duration= System.currentTimeMillis()-startTime;

        while (duration< 6000) {
            int amountOfNewPopulationMembers = 0;

            rand = random.nextInt(100);

            //MUTATE
            if (rand < 20) {
                int which = randPopulationMember();
                SittingPlan mutated = mutate(population.get(which));
                population.set(which, mutated);
            }
            //MUTATE BIG
            else if (rand < 25) {
                int which = randPopulationMember();
                SittingPlan mutated = mutateBig(population.get(which));
                population.set(which, mutated);
            } else {
                ++amountOfNewPopulationMembers;
                int which1 = randPopulationMember();
                int which2 = randPopulationMember();
                SittingPlan newMember = copulate(population.get(which1), population.get(which2));
                population.add(newMember);
            }
            perfect = isTherePerfectOne(population);
            if(perfect!=null){
                break;
            }
                population = naturalSelection(population, amountOfNewPopulationMembers);

            currentBest=getCurrentBest(population);

            duration= System.currentTimeMillis()-startTime;
        }
        if(perfect!=null) {
            return perfect;
        }
        else {
            return currentBest;
        }
    }

    private int randPopulationMember() {
        return random.nextInt(populationSize);
    }

    private SittingPlan getCurrentBest(List<SittingPlan> sittingPlans) {
        SittingPlan currentBest= sittingPlans.get(0);
        int currentAdaptationLvl=currentBest.getAdaptationLvl();
            for (SittingPlan sittingPlan : sittingPlans) {
                if (sittingPlan.getAdaptationLvl() > currentAdaptationLvl) {
                    currentBest= sittingPlan;
                    currentAdaptationLvl=currentBest.getAdaptationLvl();
                }
            }
            return currentBest;

    }
}
