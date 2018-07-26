package pl.mylittleworld.usadz_ich;

import org.junit.Test;

public class SittingPlanProxyTest {

    @Test
    public void t() {
        SittingPlan sittingPlan = new SittingPlan();
        SittingPlanProxy sittingPlanProxy = new SittingPlanProxy(sittingPlan);
        Seat seat = sittingPlanProxy.whereSits(0);
        seat.setTableID(3);
    }
}
