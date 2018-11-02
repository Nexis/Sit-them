package pl.mylittleworld.usadz_ich.genetics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

import pl.mylittleworld.database.Seat;
import pl.mylittleworld.database.tables.ChairT;
import pl.mylittleworld.database.tables.ConditionT;
import pl.mylittleworld.database.tables.PersonT;
import pl.mylittleworld.usadz_ich.SittingPlan;
import pl.mylittleworld.usadz_ich.conditions.Condition;
import pl.mylittleworld.usadz_ich.conditions.Conditions;
import pl.mylittleworld.usadz_ich.logic.Control;

public class GeneticAlgorithms {

    private Random random = new Random();

    private final int populationSize = 10;

    private ArrayList<ChairT> chairTS;
    private ArrayList<PersonT> people;
    private Conditions conditionTS;

    public GeneticAlgorithms(ArrayList<ChairT> chairTS, ArrayList<PersonT> people, Conditions conditionTS) {
        this.chairTS = chairTS;
        this.people = people;
        this.conditionTS = conditionTS;
    }

    SittingPlan getNewEmptySittingPlan() {

        ArrayList<Seat> seats= new ArrayList<>();

        for(ChairT chairT : chairTS){
            seats.add(new Seat(chairT,-1));
        }

        return new SittingPlan(seats,conditionTS,people);

    }

    List<SittingPlan> randFirstPopulation(SittingPlan emptySittingPlan) {

        List<SittingPlan> sittingPlans = new ArrayList<SittingPlan>();
        for (int i = 0; i < populationSize; ++i) {
            sittingPlans.add(emptySittingPlan);
        }

        int randPersonIndex;
        int numberOfSits = emptySittingPlan.getNumberOfSits();

        for (SittingPlan sittingPlan : sittingPlans) {
            randPersonIndex = random.nextInt() % numberOfSits;
            for (int i = 0; i < numberOfSits; ++i) {
                while (sittingPlan.isPersonUnderThisIndexSitted(randPersonIndex)) {
                    randPersonIndex = random.nextInt() % numberOfSits;
                }
                sittingPlan.getSitAt(i).setPersonID(randPersonIndex);
            }
        }
        return sittingPlans;
    }

    SittingPlan mutateBig(SittingPlan sittingPlan) {

        int startIndex = Math.abs(random.nextInt() % sittingPlan.getNumberOfSits());
        int endIndex = Math.abs(random.nextInt() % sittingPlan.getNumberOfSits());

        if (startIndex > endIndex) {
            int temp = startIndex;
            startIndex = endIndex;
            endIndex = temp;
        }

        SittingPlan mutated = new SittingPlan(sittingPlan);
        int backIndex = endIndex;

        for (int i = 0; i < sittingPlan.getNumberOfSits(); ++i) {
            if (i < startIndex || i > endIndex) {
                mutated.getSitAt(i).setPersonID(sittingPlan.getSitAt(i).getPersonID());
            } else {
                mutated.getSitAt(i).setPersonID(sittingPlan.getSitAt(backIndex).getPersonID());
                --backIndex;
            }
        }
        return mutated;

    }

    SittingPlan mutate(SittingPlan sittingPlan) {
        if (sittingPlan.getNumberOfSits() > 1) {
            int sitNumber1 = Math.abs(random.nextInt() % sittingPlan.getNumberOfSits());
            int sitNumber2 = Math.abs(random.nextInt() % sittingPlan.getNumberOfSits());

            while (sitNumber1 == sitNumber2) {
                sitNumber2 = Math.abs(random.nextInt() % sittingPlan.getNumberOfSits());
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

        int startOfMotherGenom = Math.abs(random.nextInt()) % numberOfSits;
        int endOfMotherGenom = (startOfMotherGenom + numberOfSits / 2) % numberOfSits;

        if (startOfMotherGenom > endOfMotherGenom) {
            int temp = startOfMotherGenom;
            startOfMotherGenom = endOfMotherGenom;
            endOfMotherGenom = temp;
        }


        Seat[] descendantTable = new Seat[numberOfSits];
        SittingPlan temporarySittingPlan = new SittingPlan();

        for (int index = startOfMotherGenom; index < endOfMotherGenom; ++index) {
            descendantTable[index] = mother.getSitAt(index);
            temporarySittingPlan.addSit(mother.getSitAt(index));
        }

        for (int i = 0, index = 0; i < numberOfSits; ++i) {
            if (i == startOfMotherGenom) {
                i = endOfMotherGenom;
            }
            while (temporarySittingPlan.isThisPersonSitted(father.getSitAt(index).getPersonID())) {
                ++index;
            }
            descendantTable[i] = father.getSitAt(index);
            ++index;
        }

        // rewrite sitting plan in proper order to List
        SittingPlan descendant = new SittingPlan();
        for (int i = 0; i < numberOfSits; ++i) {
            descendant.addSit(descendantTable[i]);
        }
        return descendant;
    }

    List<SittingPlan> naturalSelection(List<SittingPlan> sittingPlans) {

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

        int listSize = sittingPlans.size() / 2;

        for (int i = 0; i < listSize; ++i) {
            sittingPlans.remove(0);
        }

        return sittingPlans;
    }
    private isTherePerfectOne()

    public SittingPlan evolution() throws SomethingWentTerriblyWrongException{

        List<SittingPlan> population= randFirstPopulation(getNewEmptySittingPlan());

        boolean find=false;
        int rand;

        while(find==false){
            int amountOfNewPopulationMembers=0;

            rand=Math.abs(random.nextInt())%100;

            //MUTATE
            if(rand<70){
                int which=Math.abs(random.nextInt())%populationSize;
               SittingPlan mutated=mutate(population.get(which));
               population.set(which,mutated);
            }
            //MUTATE BIG
            else if(rand<85){
                int which=Math.abs(random.nextInt())%populationSize;
                SittingPlan mutated=mutateBig(population.get(which));
                population.set(which,mutated);
            }
            else{
                ++amountOfNewPopulationMembers;
                int which1=Math.abs(random.nextInt())%populationSize;
                int which2=Math.abs(random.nextInt())%populationSize;
                SittingPlan newMember=copulate(population.get(which1),population.get(which2));
                population.add(newMember);
            }
           for(int i=0;i<amountOfNewPopulationMembers;++i){
                population=naturalSelection(population);
           }

        }

        return null;
    }
}
