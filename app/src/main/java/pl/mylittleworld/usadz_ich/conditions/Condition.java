package pl.mylittleworld.usadz_ich.conditions;

import pl.mylittleworld.usadz_ich.SittingPlan;

public interface Condition {

    boolean isThisConditionFulfilled(SittingPlan sittingPlanProxy);

    String getDescription();

   int getConditionId();
}
