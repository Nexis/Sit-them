package pl.mylittleworld.sit_them;

import pl.mylittleworld.sit_them.model.Seat;

public class SittingPlanProxy {

    private SittingPlan sittingPlan;

    public SittingPlanProxy(SittingPlan sittingPlan) {
        this.sittingPlan = sittingPlan;
    }

    public Seat whereSits(int personID){
        return sittingPlan.whereSits(personID);
    }
}
