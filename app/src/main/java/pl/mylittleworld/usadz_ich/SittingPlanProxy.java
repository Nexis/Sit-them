package pl.mylittleworld.usadz_ich;

public class SittingPlanProxy {

    private SittingPlan sittingPlan;

    public SittingPlanProxy(SittingPlan sittingPlan) {
        this.sittingPlan = sittingPlan;
    }

    public Seat whereSits(int personID){
        return sittingPlan.whereSits(personID);
    }
}
