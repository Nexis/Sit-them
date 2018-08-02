package pl.mylittleworld.usadz_ich.genetics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import pl.mylittleworld.usadz_ich.Seat;
import pl.mylittleworld.usadz_ich.SittingPlan;
import pl.mylittleworld.usadz_ich.SittingPlanProxy;

public class GeneticAlgorithms {

    private Random random = new Random();

    private final int populationSize=10;

    private List<SittingPlan>  randFirstPopulation(SittingPlan emptySittingPlan){

        List<SittingPlan> sittingPlans= new ArrayList<SittingPlan>();
        for(int i=0;i<populationSize;++i){
            sittingPlans.add(emptySittingPlan);
        }

        int randPersonIndex;
        int numberOfSits= emptySittingPlan.getNumberOfSits();

        for(SittingPlan sittingPlan : sittingPlans){
            randPersonIndex=random.nextInt()%numberOfSits;
            for(int i=0;i<numberOfSits;++i) {
                while(sittingPlan.isPersonUnderThisIndexSitted(randPersonIndex)){
                    randPersonIndex=random.nextInt()%numberOfSits;
                }
                sittingPlan.getSitAt(i).setPersonID(randPersonIndex);
            }
        }
        return sittingPlans;
    }

    private SittingPlan mutateBig(SittingPlan sittingPlan){

        int startIndex= random.nextInt()% sittingPlan.getNumberOfSits();
        int endIndex= random.nextInt()% sittingPlan.getNumberOfSits();

        if(startIndex> endIndex){
            int temp= startIndex;
            startIndex=endIndex;
            endIndex=temp;
        }

        SittingPlan mutated= new SittingPlan();
        int backIndex=endIndex;

        for(int i=0;i<sittingPlan.getNumberOfSits();++i){
            if(i<startIndex || i>endIndex){
                mutated.addSit(sittingPlan.getSitAt(i));
            }
            else{
                mutated.addSit(sittingPlan.getSitAt(backIndex));
                --backIndex;
            }
        }
        return mutated;
    }

    private SittingPlan mutate(SittingPlan sittingPlan) {
        if (sittingPlan.getNumberOfSits() > 1) {
            int sitNumber1 = random.nextInt() % sittingPlan.getNumberOfSits();
            int sitNumber2 = random.nextInt() % sittingPlan.getNumberOfSits();

            while (sitNumber1 == sitNumber2) {
                sitNumber2 = random.nextInt() % sittingPlan.getNumberOfSits();
            }
            sittingPlan.swapPeopleAtSits(sitNumber1, sitNumber2);

        }
        return sittingPlan;
    }

    private SittingPlan copulate(SittingPlan mother, SittingPlan father) throws SomethingWentTerriblyWrongException {
        if (mother.getNumberOfSits() != father.getNumberOfSits()) {
            throw new SomethingWentTerriblyWrongException();
        }
        int numberOfSits = mother.getNumberOfSits();

        int startOfMotherGenom = random.nextInt() % numberOfSits;
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
            if (i >= startOfMotherGenom) {
                i = endOfMotherGenom;
            }
            while (temporarySittingPlan.isThisPersonSitted(father.getSitAt(index).getPersonID())) {
                ++index;
            }
            descendantTable[i] = father.getSitAt(index);
        }

        // rewrite sitting plan in proper order to List
        SittingPlan descendant = new SittingPlan();
        for (int i = 0; i < numberOfSits; ++i) {
            descendant.addSit(descendantTable[i]);
        }
        return descendant;
    }


    public List<SittingPlan> naturalSelection(List<SittingPlan> sittingPlans) {

        Collections.sort(sittingPlans,new Comparator<SittingPlan>() {
            @Override
            public int compare(SittingPlan o1, SittingPlan o2) {
                if (o1.getAdaptationLvl()<o2.getAdaptationLvl()){
                    return -1;
                }else if (o1.getAdaptationLvl()==o2.getAdaptationLvl()){
                    return 0;
                }else{
                    return 1;
                }

            }
        });

        int listSize=sittingPlans.size()/2;

        for(int i=0;i<listSize;++i) {
            sittingPlans.remove(i);
        }

        return sittingPlans;
    }

    public void evolution(){


    }
}
