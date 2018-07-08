package pl.mylittleworld.usadz_ich.conditions;

import pl.mylittleworld.usadz_ich.SittingPlanProxy;

public interface Condition {

    boolean isThisConditionFulfilled(SittingPlanProxy sittingPlanProxy);
}
