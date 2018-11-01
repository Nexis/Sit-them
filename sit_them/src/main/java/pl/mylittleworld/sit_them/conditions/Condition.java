package pl.mylittleworld.sit_them.conditions;

import pl.mylittleworld.sit_them.SittingPlanProxy;

public interface Condition {

    boolean isThisConditionFulfilled(SittingPlanProxy sittingPlanProxy);

    String getDescription();
}
