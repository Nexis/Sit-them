package pl.mylittleworld.usadz_ich;

import java.util.List;
import java.util.Random;

public class GeneticAlgorithms {

    private Random random=new Random();


    public SittingPlan mutate(SittingPlan sittingPlan){
        if(sittingPlan.getNumberOfSits()>1) {
            int sitNumber1 = random.nextInt() % sittingPlan.getNumberOfSits();
            int sitNumber2 = random.nextInt() % sittingPlan.getNumberOfSits();

            while (sitNumber1 == sitNumber2) {
                sitNumber2 = random.nextInt() % sittingPlan.getNumberOfSits();
            }
            sittingPlan.swapPeopleAtSits(sitNumber1,sitNumber2);

        }
        return sittingPlan;
    }

    public List<SittingPlan> naturalSelection(List<SittingPlan> sittingPlans){

        return sittingPlans;
    }

}
