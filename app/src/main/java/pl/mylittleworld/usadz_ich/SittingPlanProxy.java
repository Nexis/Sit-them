package pl.mylittleworld.usadz_ich;

import pl.mylittleworld.usadz_ich.Sit;

public class SittingPlanProxy {

    private SittingPlan sittingPlan;

    public SittingPlanProxy(SittingPlan sittingPlan) {
        this.sittingPlan = sittingPlan;
    }

    public Sit whereSits(int personID){
        return sittingPlan.whereSits(personID);
    }
}
